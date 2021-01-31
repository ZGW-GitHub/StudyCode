package com.code.spring.ioc.bean.instantiation.basic.staticmethod;

import com.code.spring.MySpringApplicationTest;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Spring Bean 的实例化方式 ：通过静态工厂方法
 *
 * @author 愆凡
 * @date 2021/1/31 22:04
 */
public class BeanInstantiationTest extends MySpringApplicationTest {

	@Test
	public void byXmlTest() {
		BeanFactory beanFactory = new ClassPathXmlApplicationContext(
				"classpath:/META-INF/ioc/bean/instantiation/bean-instantiation-static-method.xml");

		TestUser user = beanFactory.getBean("user", TestUser.class);

		System.err.println(user);
	}

}
