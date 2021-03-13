package com.code.data.redis.client.test;

import com.code.data.redis.client.DataRedisClientApplicationTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author 愆凡
 * @date 2021/3/12 09:06
 */
public class LockTest extends DataRedisClientApplicationTest {

	@Autowired
	private RedisTemplate redisTemplate;

	@Test
	public void overTest() {

	}

	@Test
	public void testOne() {
		
	}

}
