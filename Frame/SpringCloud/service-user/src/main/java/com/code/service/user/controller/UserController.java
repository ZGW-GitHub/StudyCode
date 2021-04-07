package com.code.service.user.controller;

import com.code.service.common.entity.User;
import com.code.service.user.service.UserService;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 愆凡
 * @date 2021/4/7 16:31
 */
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	/**
	 * 查询用户信息
	 * 
	 * @param userid userid
	 * @return {@link User}
	 */
	@RequestMapping("/{id}")
	public User getUser(@PathVariable("id") Long userid) {
		User user = userService.findById(userid);

		System.err.println("查询到的用户信息：" + ToStringBuilder.reflectionToString(user));
		
		return user;
	}

}
