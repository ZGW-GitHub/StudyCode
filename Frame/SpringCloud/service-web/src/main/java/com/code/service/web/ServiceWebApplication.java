package com.code.service.web;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author 愆凡
 * @date 2021/4/7 15:06
 */
@EnableEurekaClient
@SpringBootApplication
public class ServiceWebApplication {

	public static void main(String[] args) {
		
		new SpringApplicationBuilder().sources(ServiceWebApplication.class).run(args);
		
	}
	
}
