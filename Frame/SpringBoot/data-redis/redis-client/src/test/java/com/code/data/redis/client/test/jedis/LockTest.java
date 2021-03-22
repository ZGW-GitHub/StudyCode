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
	
	@Autowired
	private LockUtil lockUtil;
	
	private Jedis jedis;

	private final String key = "keyTest";
	private final String value = "requstid";
	private final long expireTime = 100000;
	
	@Before
	public void before() {
		jedis = jedisPool.getResource();
	}

	/**
	 * 获取分布式锁（不可重入）
	 */
	@Test
	public void lockTest() {
		boolean isLock = lockUtil.tryLock(jedis, key, value, expireTime);
		System.err.println("尝试获取分布式锁：" + isLock);
	}

	/**
	 * 解除分布式锁（不可重入）
	 */
	@Test
	public void unlockTest() {
		boolean isUnLock = lockUtil.tryUnLock(jedis, key, value);
		System.err.println("尝试释放分布式锁：" + isUnLock);
	}

	/**
	 * 获取可重入分布式锁
	 */
	@Test
	public void reentrantLockByHashTest() {
		boolean isLock = lockUtil.tryReentrantLockByHash(jedis, key, value, expireTime);
		System.err.println("尝试获取可重入分布式锁：" + isLock);
	}

	/**
	 * 解除可重入分布式锁
	 */
	@Test
	public void unReentrantLockByHashTest() {
		boolean isUnLock = lockUtil.tryUnReentrantLockByHash(jedis, key, value);
		System.err.println("尝试释放可重入分布式锁：" + isUnLock);
	}

	/**
	 * 阻塞获取分布式锁
	 */
	@Test
	public void blockLockTest() throws InterruptedException {
		boolean isLock = lockUtil.blockLock(jedis, key, value, expireTime);
		System.err.println("尝试获取分布式锁：" + isLock);
	}

	/**
	 * 解除分布式锁（不可重入）
	 */
	@Test
	public void blockUnLockTest() {
		boolean isUnLock = lockUtil.tryUnLock(jedis, key, value);
		System.err.println("尝试释放分布式锁：" + isUnLock);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
