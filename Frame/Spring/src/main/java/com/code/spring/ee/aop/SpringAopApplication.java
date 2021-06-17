package com.code.spring.ee.aop;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author 愆凡
 * @date 2021/6/17 21:44
 */
@SpringBootApplication(scanBasePackages = {"com.code.spring.aa.basic", "com.code.spring.ee.aop"})
public class SpringAopApplication {
	public static void main(String[] args) {

		new SpringApplicationBuilder(SpringAopApplication.class).run(args);

	}
}
