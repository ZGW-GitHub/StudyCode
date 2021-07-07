package com.code.service.web.cnotroller;

import com.code.service.api.entity.User;
import com.code.service.api.service.UserService;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 愆凡
 * @date 2021/4/7 18:38
 */
@RestController
@RequestMapping("/login")
public class LoginController {

	@DubboReference
	private UserService userService;

	@RequestMapping("/{id}")
	public String login(@PathVariable("id") Long userid) {
		// 调用 User 服务查询用户是否存在
		User user = userService.findById(userid);

		System.err.println("登录的用户信息：" + ToStringBuilder.reflectionToString(user));

		if (user != null) {
			return user.getId() + "_" + user.getName() + "，登录成功！";
		}
		return "登录失败！";
	}

}
