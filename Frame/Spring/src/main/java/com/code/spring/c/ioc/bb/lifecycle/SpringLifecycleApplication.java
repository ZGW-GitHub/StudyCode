package com.code.spring.c.ioc.bb.lifecycle;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author 愆凡
 * @date 2021/6/18 15:50
 */
@Slf4j
@SpringBootApplication(scanBasePackages = {"com.code.spring.a.basic", "com.code.spring.c.ioc.bb.lifecycle"})
public class SpringLifecycleApplication {
	public static void main(String[] args) {

		new SpringApplicationBuilder(SpringLifecycleApplication.class).run(args);

	}
}
