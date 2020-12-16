package com.code.nacos.service.provider.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 愆凡
 * @date 2020/12/16 5:22 下午
 */
@RestController
@RequestMapping("/demo")
public class DemoController {

	@GetMapping("/get/{param}")
	public String get(@PathVariable String param) {
		return "This is provider，param ：" + param;
	}

}
