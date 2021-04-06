package com.code.spring.zc.schema;

import com.code.spring.MySpringApplicationTest;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author 愆凡
 * @date 2021/2/3 10:46
 */
public class SchemaTest extends MySpringApplicationTest {

	@Test
	public void test() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:/META-INF/test.xml");

		MyApplicationConfig config = context.getBean(MyApplicationConfig.class);

		System.err.println(config);
	}

}
