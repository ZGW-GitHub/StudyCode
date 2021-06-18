package com.code.spring.e.aop;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author 愆凡
 * @date 2021/6/17 21:44
 */
@SpringBootApplication(scanBasePackages = {"com.code.spring.a.basic", "com.code.spring.e.aop"})
public class SpringAopApplication {
	public static void main(String[] args) {

		new SpringApplicationBuilder(SpringAopApplication.class).run(args);

	}
}
