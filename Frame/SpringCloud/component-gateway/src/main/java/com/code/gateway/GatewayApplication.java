package com.code.gateway;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author 愆凡
 * @date 2021/4/7 17:05
 */
@SpringBootApplication
public class GatewayApplication {

	public static void main(String[] args) {
		
		new SpringApplicationBuilder().sources(GatewayApplication.class).run(args);
		
	}
	
}
