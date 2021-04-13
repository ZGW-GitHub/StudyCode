package com.code.service.user.service;

import com.code.service.api.entity.User;
import com.code.service.api.service.UserService;
import com.code.service.user.configuration.NacosConfiguration;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * @author 愆凡
 * @date 2021/4/7 18:48
 */
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private NacosConfiguration nacosConfiguration;
	
	@Override
	@RefreshScope
	public User findById(Long id) {
		return User.builder().id(nacosConfiguration.getId()).name(nacosConfiguration.getName()).build();
	}
	
}
