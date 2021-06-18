package com.code.spring.c.ioc.bean.lifecycle;

import com.code.spring.MySpringApplicationTest;
import lombok.Getter;
import lombok.ToString;
import org.junit.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;

/**
 * @author 愆凡
 * @date 2020/12/27 22:22
 */
public class BeanAwareTest extends MySpringApplicationTest {

	@Test
	public void test() {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext();
		applicationContext.setConfigLocation("TEST-FILE/cc/ioc/bean/lifecycle/bean-aware.xml");

		// 启动应用上下文
		applicationContext.refresh();

		AwareEntity awareEntity = applicationContext.getBean("awareEntity", AwareEntity.class);
		System.err.println(awareEntity);
	}

	@Getter
	@ToString
	@SuppressWarnings("NullableProblems")
	private static class AwareEntity implements BeanNameAware, BeanFactoryAware, BeanClassLoaderAware, EnvironmentAware {

		private String beanName;
		private ClassLoader classLoader;
		private BeanFactory beanFactory;
		private Environment environment;

		@Override
		public void setBeanName(String beanName) {
			this.beanName = beanName;
		}

		@Override
		public void setBeanClassLoader(ClassLoader classLoader) {
			this.classLoader = classLoader;
		}

		@Override
		public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
			this.beanFactory = beanFactory;
		}

		@Override
		public void setEnvironment(Environment environment) {
			this.environment = environment;
		}

	}

}
