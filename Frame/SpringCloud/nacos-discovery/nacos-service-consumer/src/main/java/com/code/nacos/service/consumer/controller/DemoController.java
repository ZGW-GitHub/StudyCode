package com.code.nacos.service.consumer.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 愆凡
 * @date 2020/12/16 5:22 下午
 */
@RestController
@RequiredArgsConstructor
public class DemoController {

	/**
	 * 用于获取服务实例信息
	 */
	private final DiscoveryClient discoveryClient;

	@GetMapping(value = "/get")
	public List<ServiceInstance> get() {
		// 从远端获取服务 demo-provider 的实例
		return discoveryClient.getInstances("NacosService-Provider");
	}

}
