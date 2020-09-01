package com.code.data.redis.test;

import com.code.data.redis.DataRedisApplicationTest;
import com.code.data.redis.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.io.Serializable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

/**
 * Redis 测试
 *
 * @author zgw
 * @date 2020/8/28 4:27 下午
 */
@Slf4j
public class RedisTest extends DataRedisApplicationTest {

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Autowired
	private RedisTemplate<String, Serializable> redisCacheTemplate;

	/**
	 * 测试 Redis 线程安全，程序结束查看 redis 中 count 的值是否为 1000
	 */
	@Test
	public void threadTest() {
		ExecutorService executorService = Executors.newFixedThreadPool(1000);
		IntStream.range(0, 1000).forEach(i ->
				executorService.execute(() -> stringRedisTemplate.opsForValue().increment("count", 1)));

		stringRedisTemplate.opsForValue().get("count");
	}

	@Test
	public void stringTest() {
		stringRedisTemplate.opsForValue().set("k1", "v1");

		String k1 = stringRedisTemplate.opsForValue().get("k1");

		log.debug("【 k1 】= {}", k1);
	}

	@Test
	public void ObjTest() {
		String key = "user:1";
		redisCacheTemplate.opsForValue().set(key, User.builder().id(1L).name("user").build());

		User user = (User) redisCacheTemplate.opsForValue().get(key);

		log.debug("【 user 】= {}", user);
	}

}
