package com.code.service.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 愆凡
 * @date 2021/4/11 15:00
 */
@RefreshScope
@RestController
public class OtherController {

	@Autowired
	private DiscoveryClient discoveryClient;

	@Value("${config:nothing}")
	private String config;

	@GetMapping("/getDiscovery")
	public DiscoveryClient getDiscovery() {
		return discoveryClient;
	}

	@GetMapping("/getConfig")
	public String getConfig() {
		return config;
	}

}
