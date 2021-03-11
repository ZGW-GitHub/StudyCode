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

	/**
	 * 调用该方法后，会将返回值缓存（ key = "userCache::#user.id" ）；
	 * 若无返回值会缓存错误信息
	 *
	 * @param user user
	 */
	@CachePut(value = "userCache", key = "#user.id")
	public User save(User user) {
		return userRepository.save(user);
	}

	/**
	 * 调用该方法时，会先从名为 userCache 的缓存中找，找到了直接返回；
	 * 找不到，执行方法体中的代码，并将方法的返回值缓存起来（ key = "userCache::#id" ）
	 *
	 * @param id key
	 * @return user
	 */
	@Cacheable(value = "userCache", key = "#id")
	public User get(Integer id) {
		return userRepository.findById(id).get();
	}

	/**
	 * 调用该方法后会将 key="userCache::#id" 的缓存从名为 userCache 的缓存中删除
	 *
	 * @param id userid
	 */
	@CacheEvict(value = "userCache", key = "#id")
	public void delete(Integer id) {
		userRepository.deleteById(id);
	}

}
