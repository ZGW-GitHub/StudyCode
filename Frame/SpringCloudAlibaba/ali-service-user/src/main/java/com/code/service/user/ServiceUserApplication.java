package com.code.service.user;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author 愆凡
 * @date 2021/4/7 18:26
 */
@EnableDubbo(scanBasePackages = {"com.code.service.user"})
@EnableDiscoveryClient
@SpringBootApplication
public class ServiceUserApplication {

	public static void main(String[] args) {
		
		new SpringApplicationBuilder(ServiceUserApplication.class).run(args);
		
	}
	
}
