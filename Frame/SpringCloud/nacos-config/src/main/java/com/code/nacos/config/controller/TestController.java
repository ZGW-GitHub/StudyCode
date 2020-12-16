package com.code.nacos.config.controller;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.context.environment.EnvironmentManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 愆凡
 * @date 2020/12/9 1:52 下午
 */
@RefreshScope
@RestController
@RequestMapping("/config")
public class TestController {
	
	@Autowired
	private EnvironmentManager environmentManager;
	
	@Value("${age:10}")
	private Integer age;
	
	@RequestMapping("/get")
	public Integer get() {
		System.err.println(ToStringBuilder.reflectionToString(environmentManager));
		return age;
	}
	
}
