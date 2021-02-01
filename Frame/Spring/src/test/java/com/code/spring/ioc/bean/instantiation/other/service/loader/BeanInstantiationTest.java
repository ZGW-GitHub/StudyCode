package com.code.spring.ioc.bean.instantiation.other.service.loader;

import com.code.spring.MySpringApplicationTest;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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

	/**
	 * ServiceLoaderFactoryBean 示例
	 * @throws Exception
	 */
	@Test
	@SuppressWarnings("all")
	public void serviceLoaderFactoryBeanTest() throws Exception {
		BeanFactory beanFactory = new ClassPathXmlApplicationContext(
				"classpath:/META-INF/ioc/bean/instantiation/bean-instantiation-service-loader.xml");

		ServiceLoader<TestUserFactory> serviceLoader = beanFactory.getBean("serviceLoaderFactoryBean", ServiceLoader.class);

		serviceLoader.forEach(testUserFactory -> System.err.println(testUserFactory.getUser()));
	}

	/**
	 * ServiceFactoryBean 示例
	 */
	@Test
	public void serviceFactoryBeanTest() {
		BeanFactory beanFactory = new ClassPathXmlApplicationContext(
				"classpath:/META-INF/ioc/bean/instantiation/bean-instantiation-service-loader.xml");

		TestUserFactory testUserFactory = beanFactory.getBean("serviceFactoryBean", TestUserFactory.class);

		System.err.println(testUserFactory.getUser());
	}

	/**
	 * ServiceListFactoryBean 示例
	 */
	@Test
	@SuppressWarnings("all")
	public void serviceListFactoryBeanTest() {
		BeanFactory beanFactory = new ClassPathXmlApplicationContext(
				"classpath:/META-INF/ioc/bean/instantiation/bean-instantiation-service-loader.xml");

		List<TestUserFactory> testUserFactorys = beanFactory.getBean("serviceListFactoryBean", List.class);

		testUserFactorys.forEach(testUserFactory -> System.err.println(testUserFactory.getUser()));
	}

}
