package com.code.service.user.service;

import com.code.service.api.entity.User;
import com.code.service.api.service.UserService;
import com.code.service.user.configuration.NacosConfiguration;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;

/**
 * @author 愆凡
 * @date 2021/4/7 18:48
 */
@DubboService
public class UserServiceImpl implements UserService {

	@Resource
	private NacosConfiguration nacosConfiguration;

	@Override
	public User findById(Long id) {
		return User.builder().id(nacosConfiguration.getId()).name(nacosConfiguration.getName()).build();
	}

}
