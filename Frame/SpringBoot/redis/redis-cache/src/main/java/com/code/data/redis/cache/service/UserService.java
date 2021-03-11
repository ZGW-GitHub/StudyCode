package com.code.data.redis.cache.service;

import com.code.data.redis.cache.entity.User;
import com.code.data.redis.cache.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author 愆凡
 * @date 2020/9/1 9:20 上午
 */
@Slf4j
@Service
@SuppressWarnings("all")
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@CachePut(key = "#user.id", value = "user")
	public void saveOrUpdate(User user) {
		userRepository.save(user);
	}

	@Cacheable(key = "#id", value = "user")
	public User get(Integer id) {
		return userRepository.findById(id).get();
	}

	@CacheEvict(key = "#id", value = "user")
	public void delete(Integer id) {
		userRepository.deleteById(id);
	}

}
