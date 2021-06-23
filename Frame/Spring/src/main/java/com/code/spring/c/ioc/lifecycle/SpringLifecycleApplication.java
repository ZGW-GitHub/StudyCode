package com.code.spring.c.ioc.lifecycle;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author 愆凡
 * @date 2021/6/18 15:50
 */
@Slf4j
@SpringBootApplication(scanBasePackages = {"com.code.spring.a.basic", "com.code.spring.c.ioc.lifecycle"})
public class SpringLifecycleApplication {
	public static void main(String[] args) {

//		test(args);
		initPropertySourcesTest(args);

	}

	private static void test(String[] args) {
		new SpringApplicationBuilder(SpringLifecycleApplication.class).run(args);
	}

	/**
	 * 测试生命周期方法(initPropertySources)的扩展
	 *
	 * @param args args
	 */
	private static void initPropertySourcesTest(String[] args) {
		new SpringApplicationBuilder(SpringLifecycleApplication.class)
				.contextClass(MyAnnotationConfigApplicationContext.class)
				.run(args);
	}

}
