package com.code.spring.cc.ioc.bean.instantiation.basic.factory.bean;

import com.code.spring.MySpringApplicationTest;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Spring Bean 的实例化方式 ：通过 FactoryBean
 *
 * @author 愆凡
 * @date 2021/1/31 22:04
 */
public class BeanInstantiationTest extends MySpringApplicationTest {

	@Test
	public void byXmlTest() {
		BeanFactory beanFactory = new ClassPathXmlApplicationContext(
				"classpath:/TEST-FILE/cc/ioc/bean/instantiation/bean-instantiation-factory-bean.xml");

		TestUser user = beanFactory.getBean("userFactoryBean", TestUser.class);

		System.err.println(user);
	}

}
