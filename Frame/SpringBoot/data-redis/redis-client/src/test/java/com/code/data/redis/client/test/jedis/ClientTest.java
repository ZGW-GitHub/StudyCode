package com.code.data.redis.client.test.jedis;

import com.code.data.redis.client.RedisClientApplicationTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author 愆凡
 * @date 2021/3/11 16:06
 */
public class ClientTest extends RedisClientApplicationTest {

	@Autowired
	private JedisPool jedisPool;

	@Test
	public void test() {
		Jedis jedis = jedisPool.getResource();
	}

}
