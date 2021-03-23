package com.code.data.redis.client.jedis.lock.util;

import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;
import redis.clients.jedis.params.SetParams;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.LockSupport;

/**
 * @author 愆凡
 * @date 2021/3/22 09:44
 */
@Component
public class LockUtil extends JedisPubSub {
	
	/**
	 * 尝试获取分布式锁（不可重入）
	 *
	 * @param jedis Redis 客户端
	 * @param lockKey 锁
	 * @param requestid 请求标识 ：防止加的锁被别人解锁
	 * @param expireTime 超期时间 ：防止死锁
	 * @return 是否获取成功
	 */
	public boolean tryLock(Jedis jedis, String lockKey, String requestid, long expireTime) {
		SetParams params = new SetParams();
		params.nx(); // SET IF NOT EXIST
		params.px(expireTime);

		String result = jedis.set(lockKey, requestid, params);

		return "OK".equals(result);
	}

	/**
	 * 尝试释放分布式锁（不可重入）
	 *
	 * @param jedis Redis 客户端
	 * @param lockKey 锁
	 * @param requestid 请求标识 ：防止加的锁被别人解锁
	 * @return 是否释放成功
	 */
	public boolean tryUnLock(Jedis jedis, String lockKey, String requestid) {
		// Lua 代码的作用：首先获取锁对应的 value 值，检查是否与 requestid 相等，如果相等则删除锁（使用 Lua 来确保这些操作的原子性）
		String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
		Object result = jedis.eval(script, Collections.singletonList(lockKey), Collections.singletonList(requestid));

		return "1".equals(result.toString());
	}

	/**
	 * 用来实现可重入性<br/>
	 * Map 中 key 存储锁的名称，value 存储锁的重入次数
	 */
	public final ThreadLocal<Map<String, Integer>> LOCKS = ThreadLocal.withInitial(HashMap::new);
	
	/**
	 * 尝试获取可重入分布式锁（ 使用 ThreadLocal ）
	 *
	 * @param jedis Redis 客户端
	 * @param lockKey 锁
	 * @param requestid 请求标识 ：防止加的锁被别人解锁
	 * @param expireTime 超期时间 ：防止死锁
	 * @return 是否获取成功
	 */
	public boolean tryReentrantLockByThreadLocal(Jedis jedis, String lockKey, String requestid, long expireTime) {
		Map<String, Integer> counts = LOCKS.get();

		if (counts.containsKey(lockKey)) {
			counts.put(lockKey, counts.get(lockKey) + 1);
			return true;
		} else {
			if (tryLock(jedis, lockKey, requestid, expireTime)) {
				counts.put(lockKey, 1);
				return true;
			}
		}
		return false;
	}

	/**
	 * 尝试释放可重入分布式锁（ 使用 ThreadLocal ）
	 *
	 * @param jedis Redis 客户端
	 * @param lockKey 锁
	 * @param requestid 请求标识 ：防止加的锁被别人解锁
	 * @return 是否释放成功
	 */
	public boolean tryUnReentrantLockByThreadLocal(Jedis jedis, String lockKey, String requestid) {
		Map<String, Integer> counts = LOCKS.get();

		if (counts.getOrDefault(lockKey, 0) <= 1) {
			if (tryUnLock(jedis, lockKey, requestid)) {
				counts.remove(lockKey);
				return true;
			}
			return false;
		} else {
			counts.put(lockKey, counts.get(lockKey) - 1);
			return true;
		}
	}

	/**
	 * 尝试获取可重入分布式锁（ 使用 Redis Hash ）
	 *
	 * @param jedis Redis 客户端
	 * @param lockKey 锁
	 * @param requestid 请求标识 ：防止加的锁被别人解锁
	 * @param expireTime 超期时间 ：防止死锁
	 * @return 是否获取成功
	 */
	public boolean tryReentrantLockByHash(Jedis jedis, String lockKey, String requestid, long expireTime) {
		// Lua 代码的作用：首先使用 Redis exists 命令判断当前 lock 这个锁是否存在，
		// 如果锁不存在的话，直接使用 hincrby 创建一个键为 lock 的 Hash 表，并将 Hash 表中的键 requestid 的 value 值初始为 0 再加 1 ，最后再设置过期时间。
		// 如果当前锁存在，则使用 hexists 判断当前 lock 对应的 Hash 表中是否存在 requestid 这个键，如果存在，再次使用 hincrby 加 1，最后再次设置过期时间。
		String script = "if (redis.call('exists', KEYS[1]) == 0) then redis.call('hincrby', KEYS[1], ARGV[1], 1); redis.call('pexpire', KEYS[1], ARGV[2]); return 1; end ; " +
				" if (redis.call('hexists', KEYS[1], ARGV[1]) == 1) then redis.call('hincrby', KEYS[1], ARGV[1], 1); redis.call('pexpire', KEYS[1], ARGV[2]); return 1; end ; return 0;";
		
		Object result = jedis.eval(script, Collections.singletonList(lockKey), List.of(requestid, expireTime + ""));

		return "1".equals(result.toString());
	}

	/**
	 * 尝试释放可重入分布式锁（ 使用 Redis Hash ）
	 *
	 * @param jedis Redis 客户端
	 * @param lockKey 锁
	 * @param requestid 请求标识 ：防止加的锁被别人解锁
	 * @return 是否释放成功
	 */
	public boolean tryUnReentrantLockByHash(Jedis jedis, String lockKey, String requestid) {
		// Lua 代码的作用：
		String script = "if (redis.call('hexists', KEYS[1], ARGV[1]) == 0) then return 0 end; "
				+ " local count = redis.call('hincrby', KEYS[1], ARGV[1], -1); "
				+ " if (count > 0) then return count else redis.call('del', KEYS[1]) return 1 end; ";

		Object result = jedis.eval(script, Collections.singletonList(lockKey), Collections.singletonList(requestid));

		return Integer.parseInt(result.toString()) > 0;
	}

	/**
	 * 阻塞地获取分布式锁
	 *
	 * @param jedis Redis 客户端
	 * @param lockKey 锁
	 * @param requestid 请求标识 ：防止加的锁被别人解锁
	 * @param expireTime 超期时间 ：防止死锁
	 * @return 是否获取成功
	 */
	public boolean blockLock(Jedis jedis, String lockKey, String requestid, long expireTime) {
		boolean isLock = tryLock(jedis, lockKey, requestid, expireTime);

		if (isLock) {
			return true;
		}

		Thread currThread = Thread.currentThread();
		
		jedis.subscribe(new JedisPubSub() {
			@Override
			public void punsubscribe() {
				LockSupport.unpark(currThread);
			}
		}, "test");
		
		LockSupport.park();
		
		return blockLock(jedis, lockKey, requestid, expireTime);
	}

	/**
	 * 释放分布式锁
	 *
	 * @param jedis Redis 客户端
	 * @param lockKey 锁
	 * @param requestid 请求标识 ：防止加的锁被别人解锁
	 * @return 是否释放成功
	 */
	public boolean blockUnLock(Jedis jedis, String lockKey, String requestid) {
		boolean tryUnLock = tryUnLock(jedis, lockKey, requestid);

//		if (tryUnLock) {
//			jedis.publish("test")
//		}
		return true;
	}

}
