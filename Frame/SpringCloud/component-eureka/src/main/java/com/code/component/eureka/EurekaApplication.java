package com.code.component.eureka;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author 愆凡
 * @date 2021/4/7 14:41
 */
@EnableEurekaServer
@SpringBootApplication
public class EurekaApplication {

	public static void main(String[] args) {

		new SpringApplicationBuilder().sources(EurekaApplication.class).run(args);

	}

}
