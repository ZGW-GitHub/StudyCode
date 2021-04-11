package com.code.config;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * Spring Cloud Config 分布式配置中心
 *
 * @author 愆凡
 * @date 2021/4/11 22:05
 */
@EnableConfigServer
@SpringBootApplication
public class ConfigApplication {

	public static void main(String[] args) {

		new SpringApplicationBuilder().sources(ConfigApplication.class).run(args);

	}

}
