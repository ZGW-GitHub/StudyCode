package com.code.gateway;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author 愆凡
 * @date 2021/4/7 17:05
 */
@EnableDiscoveryClient
@SpringBootApplication
public class GatewayApplication {

	public static void main(String[] args) {
		
		new SpringApplicationBuilder(GatewayApplication.class).run(args);
		
	}
	
}
