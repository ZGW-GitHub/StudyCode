package com.code.nacos.config.controller;

import com.code.nacos.config.config.AppConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 愆凡
 * @date 2020/12/9 1:52 下午
 */
@RestController
@RequestMapping("/config")
@SuppressWarnings("all")
public class TestController {
	
	@Autowired
	private AppConfiguration appConfiguration;
	
	@RequestMapping("/get")
	public Integer get() {
		return appConfiguration.getTest();
	}
	
}
