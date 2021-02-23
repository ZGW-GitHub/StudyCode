package com.code.nacos.service.provider.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 愆凡
 * @date 2020/12/16 5:22 下午
 */
@RestController
public class DemoController {

	@GetMapping("/get")
	public String get() {
		return "This is provider !";
	}

}
