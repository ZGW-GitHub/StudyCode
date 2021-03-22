package com.code.data.redis.client.jedis.lock;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.SetParams;

import java.util.Collections;

/**
 * @author 愆凡
 * @date 2021/3/22 09:44
 */
public class LockUtil {
	
	/**
	 * 尝试获取分布式锁
	 *
	 * @param jedis Redis 客户端
	 * @param lockKey 锁
	 * @param requestid 请求标识 ：防止加的锁被别人解锁
	 * @param expireTime 超期时间 ：防止死锁
	 * @return 是否获取成功
	 */
	public static boolean tryLock(Jedis jedis, String lockKey, String requestid, long expireTime) {
		SetParams params = new SetParams();
		params.px(expireTime);
		params.nx(); // SET IF NOT EXIST

		String result = jedis.set(lockKey, requestid, params);

		return "OK".equals(result);
	}

	/**
	 * 尝试释放分布式锁
	 *
	 * @param jedis Redis 客户端
	 * @param lockKey 锁
	 * @param requestid 请求标识 ：防止加的锁被别人解锁
	 * @return 是否释放成功
	 */
	public static boolean tryUnLockTest(Jedis jedis, String lockKey, String requestid) {
		// Lua 代码的作用：首先获取锁对应的 value 值，检查是否与 requestid 相等，如果相等则删除锁（使用 Lua 来确保这些操作的原子性）
		String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
		Object result = jedis.eval(script, Collections.singletonList(lockKey), Collections.singletonList(requestid));

		return "1".equals(result.toString());
	}
	
	
	
}
