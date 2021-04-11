package com.code.service.web;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 注解 {@link EnableFeignClients} 作用：将加了 {@link FeignClient} 注解的类注册为 Spring Bean
 * 
 * @author 愆凡
 * @date 2021/4/7 15:06
 */
@EnableFeignClients
@EnableDiscoveryClient
//@EnableEurekaClient
@SpringBootApplication
public class ServiceWebApplication {

	public static void main(String[] args) {
		
		new SpringApplicationBuilder().sources(ServiceWebApplication.class).run(args);
		
	}
	
}
