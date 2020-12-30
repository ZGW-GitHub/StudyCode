package com.code.spring.core.bean.lifecycle;

import com.code.spring.MySpringApplicationTest;
import com.code.spring.entity.User;
import org.junit.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.util.ObjectUtils;

/**
 * Bean 实例化 & 初始化 示例
 *
 * @author 愆凡
 * @date 2020/12/23 22:41
 */
public class BeanInstantiationAndInitializationTest extends MySpringApplicationTest {

	@Test
	public void beanPostProcessTest() {
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

		// 添加 BeanPostProcessor
		beanFactory.addBeanPostProcessor(new MyBeanPostProcessor());

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
		testThree(beanFactory);
	}

	/**
	 * 测试 Bean 的实例化、初始化阶段
	 */
	private void testOne(DefaultListableBeanFactory beanFactory) {
		User user = beanFactory.getBean("userOne", User.class);
		System.err.println(user);
	}

	/**
	 * 测试跳过 Bean 部分的实例化、初始化阶段
	 */
	private void testTwo(DefaultListableBeanFactory beanFactory) {
		User user = beanFactory.getBean("userTwo", User.class);
		System.err.println(user);
	}

	/**
	 * 测试 属性填充前阶段
	 */
	private void testThree(DefaultListableBeanFactory beanFactory) {
		User user = beanFactory.getBean("userThree", User.class);
		System.err.println(user);
	}

	/**
	 * 自定义 BeanPostProcess
	 */
	@SuppressWarnings("NullableProblems")
	static class MyBeanPostProcessor implements InstantiationAwareBeanPostProcessor {

		/**
		 * 实例化前会被调用
		 */
		@Override
		public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
			if (ObjectUtils.nullSafeEquals("userTwo", beanName)) {
				// 该对象将作为创建的 Bean对象 返回，而不再继续执行 Spring 提供的 Bean 的构建流程
				return new User();
			}
			// 继续执行 Bean 的实例化、初始化操作
			return null;
		}

		/**
		 * 实例化后会被调用
		 */
		@Override
		public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
			if (ObjectUtils.nullSafeEquals("userOne", beanName)) {
				User user = (User) bean;
				user.setId(22L);
				// "user" 对象不允许属性赋值（配置元信息 ——> 属性值）
				return false;
			}
			return true;
		}

		/* ------------------------------------------ 华丽的分割线 ------------------------------------------ */

		/**
		 * 初始化前会被调用
		 */
		@Override
		public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
			if (ObjectUtils.nullSafeEquals("userOne", beanName)) {
				User user = (User) bean;
				user.setId(222L);
				return user;
			}
			return bean;
		}

		/**
		 * 初始化后会被调用
		 */
		@Override
		public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
			if (ObjectUtils.nullSafeEquals("userTwo", beanName)) {
				User user = (User) bean;
				user.setId(11L);
				return user;
			}
			return bean;
		}

		/* ------------------------------------------ 华丽的分割线 ------------------------------------------ */

		/**
		 * 属性填充前会被调用
		 */
		@Override
		public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
			final MutablePropertyValues propertyValues;
			if (pvs instanceof MutablePropertyValues) {
				propertyValues = (MutablePropertyValues) pvs;
			} else {
				propertyValues = new MutablePropertyValues();
			}

			if (ObjectUtils.nullSafeEquals("userThree", beanName)) {
				// 配置 id = 33
				propertyValues.add("id", "33");

				// 修改配置文件中的配置
				PropertyValue namePropertyValue = propertyValues.getPropertyValue("name");
				if (namePropertyValue != null) {
					if ("愆凡3".equals(namePropertyValue.getValue())) {
						// 因为 PropertyValue 的 value 属性为 final ，不能修改，所以这里只能先删除再添加
						propertyValues.removePropertyValue("name");
						propertyValues.add("name", "愆凡33");
					}
				}
			}

			return propertyValues;
		}
	}

}
