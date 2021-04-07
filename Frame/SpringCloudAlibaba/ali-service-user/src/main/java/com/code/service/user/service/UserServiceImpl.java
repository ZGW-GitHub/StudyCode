package com.code.service.user.service;

import com.code.service.api.entity.User;
import com.code.service.api.service.UserService;
import org.apache.dubbo.config.annotation.Service;

/**
 * @author 愆凡
 * @date 2021/4/7 18:48
 */
@Service
public class UserServiceImpl implements UserService {
	
	@Override
	public User findById(Long id) {
		return User.builder().id(id).name("愆凡").build();
	}
	
}
