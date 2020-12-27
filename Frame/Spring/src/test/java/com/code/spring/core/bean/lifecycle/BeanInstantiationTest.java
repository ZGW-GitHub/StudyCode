package com.code.spring.core.bean.lifecycle;

import com.code.spring.MySpringApplicationTest;
import com.code.spring.entity.User;
import org.junit.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.util.ObjectUtils;

/**
 * Bean 实例化示例
 *
 * @author 愆凡
 * @date 2020/12/23 22:41
 */
public class BeanInstantiationTest extends MySpringApplicationTest {

	@Test
	public void beanPostProcessTest() {
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

		// 添加 BeanPostProcessor
		beanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());

		// 实例化基于 Properties 资源的 BeanDefinitionReader
		PropertiesBeanDefinitionReader beanDefinitionReader = new PropertiesBeanDefinitionReader(beanFactory);

		// Properties 资源的位置
		String file = "/META-INF/beanDefinitionLoad.properties";

		// 解决乱码
		Resource resource = new ClassPathResource(file);
		EncodedResource encodedResource = new EncodedResource(resource, "UTF-8");

		// 加载 Properties 资源
		int beanDefinitionsNum = beanDefinitionReader.loadBeanDefinitions(encodedResource);
		System.err.println("已加载 BeanDefinition 数量：" + beanDefinitionsNum);

		testOne(beanFactory);
		testTwo(beanFactory);
	}

	/**
	 * 测试 {@link InstantiationAwareBeanPostProcessor#postProcessBeforeInstantiation(Class, String)} 和
	 * {@link BeanPostProcessor#postProcessAfterInitialization(Object, String)} 方法
	 * <br/><br/> postProcessBeforeInitialization(Class<?> beanClass, String beanName) ：<br/>可以通过返回一个对象来跳过 Spring 正常的实例化操作。
	 * <br/><br/> postProcessAfterInitialization(Object bean, String beanName) ：<br/>当 postProcessBeforeInitialization 返回的对象不为 null 时，会执行该方法，此时可以对该对象进行一些自定义操作。
	 */
	private void testOne(DefaultListableBeanFactory beanFactory) {
		User user = beanFactory.getBean("userOne", User.class);
		System.err.println(user);
	}

	/**
	 * 测试 {@link BeanPostProcessor#postProcessBeforeInitialization(Object, String)} 和
	 * {@link InstantiationAwareBeanPostProcessor#postProcessAfterInstantiation(Object, String)} 方法
	 * <br/><br/> postProcessBeforeInitialization(Object bean, String beanName) ：<br/>
	 * <br/><br/> postProcessAfterInstantiation(Object bean, String beanName) ：<br/>true：允许属性赋值（配置元信息 ——> 属性值），false：不允许
	 */
	private void testTwo(DefaultListableBeanFactory beanFactory) {
		User user = beanFactory.getBean("userTwo", User.class);
		System.err.println(user);
	}

	/**
	 * 自定义 BeanPostProcess
	 */
	@SuppressWarnings("NullableProblems")
	static class MyInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {

		@Override
		public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
			if (ObjectUtils.nullSafeEquals("userOne", beanName)) {
				// 该对象将作为 createBean() 的结果返回，而不再执行 doCreateBean() 实例化对象
				return new User();
			}
			// 继续执行 Spring IoC 容器的实例化操作
			return null;
		}

		@Override
		public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
			if (ObjectUtils.nullSafeEquals("userOne", beanName)) {
				User user = (User) bean;
				user.setId(11L);
				return user;
			}
			return bean;
		}

		/* ------------------------------------------ 华丽的分割线 ------------------------------------------ */

		@Override
		public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
			if (ObjectUtils.nullSafeEquals("userTwo", beanName)) {
//				return new User();
			}
			return bean;
		}

		@Override
		public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
			if (ObjectUtils.nullSafeEquals("userTwo", beanName)) {
				User user = (User) bean;
				user.setId(22L);
				// "user" 对象不允许属性赋值（配置元信息 ——> 属性值）
				return false;
			}
			return true;
		}

	}

}
