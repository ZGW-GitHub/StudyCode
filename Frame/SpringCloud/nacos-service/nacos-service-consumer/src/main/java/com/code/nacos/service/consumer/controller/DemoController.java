package com.code.nacos.service.consumer.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author 愆凡
 * @date 2020/12/16 5:22 下午
 */
@RestController
@RequestMapping("/demo")
@RequiredArgsConstructor
public class DemoController {

	private final RestTemplate restTemplate;
	private final DiscoveryClient discoveryClient;

	@GetMapping(value = "/get/{param}")
	public String echo(@PathVariable String param) {
		// 1、获得服务 demo-provider 的一个实例
		List<ServiceInstance> instances = discoveryClient.getInstances("NacosService-Provider");
		ServiceInstance instance = instances.isEmpty() ? null : instances.get(0); // 选择第一个

		// 2、发起调用
		if (instance == null) {
			throw new IllegalStateException("获取不到实例");
		}
		String targetUrl = instance.getUri() + "/demo/get/" + param;
		String response = restTemplate.getForObject(targetUrl, String.class);

		return "consumer ：" + response;
	}

}
