package com.code.spring.cc.ioc;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author 愆凡
 * @date 2021/6/17 21:44
 */
@SpringBootApplication(scanBasePackages = {"com.code.spring.aa.basic", "com.code.spring.cc.ioc"})
public class SpringIoCApplication {
	public static void main(String[] args) {

		new SpringApplicationBuilder(SpringIoCApplication.class).run(args);

	}
}
