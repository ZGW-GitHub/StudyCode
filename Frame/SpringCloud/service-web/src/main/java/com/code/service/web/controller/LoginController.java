package com.code.service.web.controller;

import com.code.service.common.entity.User;
import com.code.service.web.openfeign.clients.UserClient;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 愆凡
 * @date 2021/4/7 16:00
 */
@RestController
@RequestMapping("/web/login")
public class LoginController {

	@Autowired
	private UserClient userClient;

	@RequestMapping("/{id}")
	public String login(@PathVariable("id") Long userid) {
		// 调用 User 服务查询用户是否存在
		User user = userClient.findById(userid);

		System.err.println("登录的用户信息：" + ToStringBuilder.reflectionToString(user));

		if (user != null) {
			return user.getId() + "_" + user.getName() + "，登录成功！";
		}
		return "登录失败！";
	}
	
}
