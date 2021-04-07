package com.code.service.web;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author 愆凡
 * @date 2021/4/7 18:28
 */
@EnableDubbo(scanBasePackages = {"com.code.service.web"})
@SpringBootApplication
public class ServiceWebApplication {

	public static void main(String[] args) {
		
		new SpringApplicationBuilder().sources(ServiceWebApplication.class).run(args);
		
	}
	
}
