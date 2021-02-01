package com.code.spring.ioc.bean.instantiation.other.autowire.capable.bean.factory;

import com.code.spring.MySpringApplicationTest;
import org.junit.Test;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Spring Bean 的实例化方式 ：AutowireCapableBeanFactory
 *
 * @author 愆凡
 * @date 2021/1/31 22:04
 */
public class BeanInstantiationTest extends MySpringApplicationTest {

	@Test
	public void test() {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"classpath:/META-INF/ioc/bean/instantiation/bean-instantiation-create-bean.xml");

		AutowireCapableBeanFactory beanFactory = applicationContext.getAutowireCapableBeanFactory();

		TestUser user = beanFactory.createBean(TestUser.class);

		System.err.println(user);
	}

}
