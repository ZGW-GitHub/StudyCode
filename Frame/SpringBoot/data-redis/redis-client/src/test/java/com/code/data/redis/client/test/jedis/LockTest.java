package com.code.data.redis.client.test.jedis;

import com.code.data.redis.client.RedisClientApplicationTest;
import com.code.data.redis.client.jedis.lock.LockUtil;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Redis 分布式锁
 *
 * @author 愆凡
 * @date 2021/3/12 09:06
 */
public class LockTest extends RedisClientApplicationTest {

	@Autowired
	private JedisPool jedisPool;
	private Jedis jedis;

	private final String key = "keyTest";
	private final String value = "requstid";
	
	@Before
	public void before() {
		jedis = jedisPool.getResource();
	}

	@Test
	public void lockTest() {
		boolean isLock = LockUtil.tryLock(jedis, key, value, 100000);
		System.err.println("尝试获取分布式锁：" + isLock);
	}

	@Test
	public void unlockTest() {
		boolean isUnLock = LockUtil.tryUnLockTest(jedis, key, value);
		System.err.println("尝试释放分布式锁：" + isUnLock);
	}

}
