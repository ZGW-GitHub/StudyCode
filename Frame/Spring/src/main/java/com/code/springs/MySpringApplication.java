package com.code.springs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author 愆凡
 * @date 2020/7/15 3:38 下午
 */
@SpringBootApplication
public class MySpringApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext context = SpringApplication.run(MySpringApplication.class, args);

		System.err.println(context.getBeanFactory().getBean("test"));

	}

}
