package com.code.spring.c.ioc.aa.bean.lifecycle;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author 愆凡
 * @date 2021/6/18 15:47
 */
@Slf4j
@SpringBootApplication(scanBasePackages = {"com.code.spring.a.basic", "com.code.spring.c.ioc.aa.bean.lifecycle"})
public class SpringBeanLifecycleApplication {
	public static void main(String[] args) {

		new SpringApplicationBuilder(SpringBeanLifecycleApplication.class).run(args);

	}
}
