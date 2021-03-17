package com.code.data.redis.client.test.jedis;

import com.code.data.redis.client.RedisClientApplicationTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.params.SetParams;

import java.util.Collections;

/**
 * Redis 分布式锁
 *
 * @author 愆凡
 * @date 2021/3/12 09:06
 */
public class LockTest extends RedisClientApplicationTest {

	@Autowired
	private JedisPool jedisPool;

	private final String key = "keyTest";
	private final String value = "requstid";

	@Test
	public void lockTest() {
		Jedis jedis = jedisPool.getResource();

		boolean isLock = tryLock(jedis, key, value, 10000);
		System.err.println("尝试获取分布式锁：" + isLock);
	}

	@Test
	public void unlockTest() {
		Jedis jedis = jedisPool.getResource();

		boolean isUnLock = tryUnLockTest(jedis, key, value);
		System.err.println("尝试释放分布式锁：" + isUnLock);
	}

	/**
	 * 尝试获取分布式锁
	 *
	 * @param jedis Redis 客户端
	 * @param lockKey 锁
	 * @param requestid 请求标识 ：防止加的锁被别人解锁
	 * @param expireTime 超期时间 ：防止死锁
	 * @return 是否获取成功
	 */
	public boolean tryLock(Jedis jedis, String lockKey, String requestid, long expireTime) {
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
	public boolean tryUnLockTest(Jedis jedis, String lockKey, String requestid) {
		String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
		Object result = jedis.eval(script, Collections.singletonList(lockKey), Collections.singletonList(requestid));

		return "1".equals(result.toString());
	}

}
