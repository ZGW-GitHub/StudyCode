package com.code.spring.n.schema;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author 愆凡
 * @date 2021/2/3 10:46
 */
public class SchemaTest {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:/META-INF/test.xml");

		MyApplicationConfig config = context.getBean(MyApplicationConfig.class);

		System.err.println(config);
	}

}
