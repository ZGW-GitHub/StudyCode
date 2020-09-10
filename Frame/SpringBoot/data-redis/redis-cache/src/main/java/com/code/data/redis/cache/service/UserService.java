package com.code.data.redis.cache.service;

import com.code.data.redis.cache.entity.User;

/**
 * @author 愆凡
 * @date 2020/9/1 9:19 上午
 */
public interface UserService {

	User saveOrUpdate(User user);

	User get(Long id);

	void delete(Long id);

}
