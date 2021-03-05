package com.code.nacos.service.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author 愆凡
 * @date 2020/12/16 5:14 下午
 */
@SpringBootApplication
@EnableDiscoveryClient
public class NacosServiceConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(NacosServiceConsumerApplication.class, args);
	}

}
