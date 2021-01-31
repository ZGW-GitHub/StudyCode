package com.code.spring.ioc.bean.instantiation.basic.constructor;

import com.code.spring.MySpringApplicationTest;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Spring Bean 的实例化方式 ：通过构造器
 *
 * @author 愆凡
 * @date 2021/1/31 22:04
 */
public class BeanInstantiationTest extends MySpringApplicationTest {

	@Test
	public void byXmlTest() {
		BeanFactory beanFactory = new ClassPathXmlApplicationContext(
				"classpath:/META-INF/ioc/bean/instantiation/bean-instantiation-constructor.xml");

		TestUser userOne = beanFactory.getBean("userOne", TestUser.class);
		TestUser userTwo = beanFactory.getBean("userTwo", TestUser.class);

		System.err.println(userOne);
		System.err.println(userTwo);
	}

}
