package com.code.data.redis.cache.service.impl;

import com.code.data.redis.cache.entity.User;
import com.code.data.redis.cache.service.UserService;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author 愆凡
 * @date 2020/9/1 9:20 上午
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

	// 模拟数据库
	private static final Map<Long, User> DATABASES = Maps.newConcurrentMap();

	// 初始化数据
	static {
		DATABASES.put(1L, User.builder().id(1L).name("user1").build());
		DATABASES.put(2L, User.builder().id(2L).name("user1").build());
		DATABASES.put(3L, User.builder().id(3L).name("user1").build());
	}

	@CachePut(value = "user", key = "#user.id")
	@Override
	public User saveOrUpdate(User user) {
		DATABASES.put(user.getId(), user);

		log.info("保存用户【user】= {}", user);

		return user;
	}

	@Cacheable(value = "user", key = "#id")
	@Override
	public User get(Long id) {
		log.info("查询用户【id】= {}", id);
		// 我们假设从数据库读取
		return DATABASES.get(id);
	}

	@CacheEvict(value = "user", key = "#id")
	@Override
	public void delete(Long id) {
		DATABASES.remove(id);
		log.info("删除用户【id】= {}", id);
	}

}
