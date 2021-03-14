package com.code.data.redis.cache.service;

import com.code.data.redis.cache.RedisCacheApplicationTest;
import com.code.data.redis.cache.entity.User;
import com.code.data.redis.cache.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Redis - 缓存测试
 *
 * @author 愆凡
 * @date 2020/9/1 11:54 上午
 */
@Slf4j
public class UserCacheTest extends RedisCacheApplicationTest {

	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepository;

	private final Integer userId = 6;
	private final User user = User.builder().id(8).name("user").age(8).build();

	@Test
	public void saveTest() {
		userRepository.deleteAll();

		userService.save(user);
	}

	@Test
	public void getTest() {
		Object user = userService.get(userId);

		System.err.println(user);
	}

	@Test
	public void deleteTest() {
		userService.delete(userId);
	}

}
