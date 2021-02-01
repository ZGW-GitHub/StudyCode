package com.code.spring.ioc.bean.instantiation.other.service.loader;

import com.code.spring.MySpringApplicationTest;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.serviceloader.ServiceFactoryBean;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;

/**
 * Spring Bean 的实例化方式 ：ServiceLoaderFactoryBean
 *
 * @author 愆凡
 * @date 2021/1/31 22:04
 */
public class BeanInstantiationTest extends MySpringApplicationTest {

	/**
	 * Java ServiceLoader 示例
	 */
	@Test
	public void javaServiceLoaderTest() {
		ServiceLoader<TestUserFactory> serviceLoader =
				ServiceLoader.load(TestUserFactory.class, Thread.currentThread().getContextClassLoader());

		serviceLoader.forEach(testUserFactory -> System.err.println(testUserFactory.getUser()));
	}

	@Test
	@SuppressWarnings("all")
	public void serviceLoaderFactoryBeanTest() throws Exception {
		BeanFactory beanFactory = new ClassPathXmlApplicationContext(
				"classpath:/META-INF/ioc/bean/instantiation/bean-instantiation-service-loader-factory-bean.xml");

		ServiceLoader<TestUserFactory> serviceLoader = beanFactory.getBean("serviceLoaderFactoryBean", ServiceLoader.class);

		serviceLoader.forEach(testUserFactory -> System.err.println(testUserFactory.getUser()));
	}

	@Test
	public void serviceFactoryBeanTest() {
		BeanFactory beanFactory = new ClassPathXmlApplicationContext(
				"classpath:/META-INF/ioc/bean/instantiation/bean-instantiation-service-loader-factory-bean.xml");

		TestUserFactory testUserFactory = beanFactory.getBean("serviceFactoryBean", TestUserFactory.class);

		System.err.println(testUserFactory.getUser());
	}

	@Test
	@SuppressWarnings("all")
	public void serviceListFactoryBeanTest() {
		BeanFactory beanFactory = new ClassPathXmlApplicationContext(
				"classpath:/META-INF/ioc/bean/instantiation/bean-instantiation-service-loader-factory-bean.xml");

		List<TestUserFactory> testUserFactorys = beanFactory.getBean("serviceListFactoryBean", List.class);

		testUserFactorys.forEach(testUserFactory -> System.err.println(testUserFactory.getUser()));
	}

}
