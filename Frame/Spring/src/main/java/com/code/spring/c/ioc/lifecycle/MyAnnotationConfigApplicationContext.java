package com.code.spring.c.ioc.lifecycle;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;

/**
 * @author 愆凡
 * @date 2021/6/18 16:32
 */
@Slf4j
public class MyAnnotationConfigApplicationContext extends AnnotationConfigServletWebServerApplicationContext {

	/**
	 * 重写方法，设置 test 为必需的属性
	 */
	@Override
	protected void initPropertySources() {
		super.initPropertySources();
		getEnvironment().setRequiredProperties("test");
	}

}
