package com.code.service.user.service;

import com.code.service.common.entity.User;
import org.springframework.stereotype.Service;

/**
 * @author 愆凡
 * @date 2021/4/7 15:31
 */
@Service
public class UserService {

	public User findById(Long id) {
		return User.builder().id(id).name("愆凡").build();
	}

}
