package com.code.spring.c.ioc.aa.bean.instantiation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author 愆凡
 * @date 2021/6/21 17:05
 */
@Slf4j
@SpringBootApplication(scanBasePackages = {"com.code.spring.a.basic", "com.code.spring.c.ioc.aa.bean.instantiation"})
public class SpringBeanInstantiationApplication {
	public static void main(String[] args) {

		new SpringApplicationBuilder(SpringBeanInstantiationApplication.class).run(args);

	}
}
