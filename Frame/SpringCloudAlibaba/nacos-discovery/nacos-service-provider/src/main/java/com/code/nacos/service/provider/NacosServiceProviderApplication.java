package com.code.nacos.service.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author 愆凡
 * @date 2020/12/16 5:14 下午
 */
@SpringBootApplication
@EnableDiscoveryClient
public class NacosServiceProviderApplication {

	public static void main(String[] args) {
		SpringApplication.run(NacosServiceProviderApplication.class, args);
	}

}