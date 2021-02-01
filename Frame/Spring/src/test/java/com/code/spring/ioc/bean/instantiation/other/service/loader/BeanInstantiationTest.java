package com.code.spring.ioc.bean.instantiation.other.service.loader;

import com.code.spring.MySpringApplicationTest;
import org.junit.Test;

import java.util.ServiceLoader;

/**
 * Spring Bean 的实例化方式 ：通过静态工厂方法
 *
 * @author 愆凡
 * @date 2021/1/31 22:04
 */
public class BeanInstantiationTest extends MySpringApplicationTest {

	@Test
	public void test() {
		ServiceLoader<TestUserFactory> testUserFactories =
				ServiceLoader.load(TestUserFactory.class, Thread.currentThread().getContextClassLoader());

		testUserFactories.forEach(testUserFactory -> System.err.println(testUserFactory.getUser()));
	}

}
