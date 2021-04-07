package com.code.service.user;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author 愆凡
 * @date 2021/4/7 15:01
 */
@EnableEurekaClient
@SpringBootApplication
public class ServiceUserApplication {

	public static void main(String[] args) {
		
		new SpringApplicationBuilder().sources(ServiceUserApplication.class).run(args);
		
	}
	
}
