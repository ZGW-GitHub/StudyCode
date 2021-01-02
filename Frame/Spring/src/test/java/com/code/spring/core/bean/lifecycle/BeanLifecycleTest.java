package com.code.spring.core.bean.lifecycle;

import com.code.spring.MySpringApplicationTest;
import org.junit.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.DestructionAwareBeanPostProcessor;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.util.ObjectUtils;

/**
 * Bean 实例化 & 初始化 & 销毁 示例
 *
 * @author 愆凡
 * @date 2020/12/23 22:41
 */
public class BeanLifecycleTest extends MySpringApplicationTest {

	private DefaultListableBeanFactory beforeTest(String fileType) {

		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

		// 添加 BeanPostProcessor
		beanFactory.addBeanPostProcessor(new MyBeanPostProcessor());

		if ("xml".equals(fileType)) {
			// 解决 @PostConstruct 方法无法回调的问题
			beanFactory.addBeanPostProcessor(new CommonAnnotationBeanPostProcessor());

			// 实例化基于 XML 资源的 BeanDefinitionReader
			XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);

			// XML 资源的位置
			String file = "/META-INF/beanDefinitionLoad.xml";

			// 解决乱码
			Resource resource = new ClassPathResource(file);
			EncodedResource encodedResource = new EncodedResource(resource, "UTF-8");

			// 加载 Properties 资源
			int beanDefinitionsNum = beanDefinitionReader.loadBeanDefinitions(encodedResource);
			System.err.println("已加载 BeanDefinition 数量：" + beanDefinitionsNum + "\n");

		} else {

			// 实例化基于 Properties 资源的 BeanDefinitionReader
			PropertiesBeanDefinitionReader beanDefinitionReader = new PropertiesBeanDefinitionReader(beanFactory);

			// Properties 资源的位置
			String file = "/META-INF/beanDefinitionLoad.properties";

			// 解决乱码
			Resource resource = new ClassPathResource(file);
			EncodedResource encodedResource = new EncodedResource(resource, "UTF-8");

			// 加载 Properties 资源
			int beanDefinitionsNum = beanDefinitionReader.loadBeanDefinitions(encodedResource);
			System.err.println("已加载 BeanDefinition 数量：" + beanDefinitionsNum + "\n");

		}

		return beanFactory;
	}

	/**
	 * 测试 Bean 的实例化、初始化阶段调用的 PostProcess 方法（这里会跳过 Bean 部分的实例化、初始化阶段）
	 */
	@Test
	public void beanPostProcessTestOne() {
		DefaultListableBeanFactory beanFactory = beforeTest(null);

		LifecycleUser user = beanFactory.getBean("userOne", LifecycleUser.class);
		System.err.println("\n" + user);
	}

	/**
	 * 测试 Bean 的实例化、初始化阶段调用的 PostProcess 方法
	 */
	@Test
	public void beanPostProcessTestTwo() {
		DefaultListableBeanFactory beanFactory = beforeTest(null);

		LifecycleUser user = beanFactory.getBean("userTwo", LifecycleUser.class);
		System.err.println("\n" + user);
	}

	/**
	 * 测试 Bean 的初始化（ @PostConstruct 、afterPropertiesSet() 、init() ）
	 */
	@Test
	public void beanPostProcessTestThree() {
		DefaultListableBeanFactory beanFactory = beforeTest("xml");

		LifecycleUser user = beanFactory.getBean("userThree", LifecycleUser.class);
		System.err.println("\n" + user);
	}

	/**
	 * 测试 Bean 的销毁（ @PreDestroy 、destroy() 、destroyDemo() ）
	 */
	@Test
	public void beanPostProcessTestFour() {
		DefaultListableBeanFactory beanFactory = beforeTest("xml");

		LifecycleUser user = beanFactory.getBean("userFour", LifecycleUser.class);
		System.err.println("\n" + user);

		beanFactory.destroyBean(user); // 销毁 Bean ，并不代表 Bean 被 GC 了
	}

	/**
	 * 自定义 BeanPostProcess
	 */
	@SuppressWarnings("NullableProblems")
	static class MyBeanPostProcessor implements InstantiationAwareBeanPostProcessor, DestructionAwareBeanPostProcessor {

		/**
		 * 实例化前会被调用
		 */
		@Override
		public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
			if (ObjectUtils.nullSafeEquals("userOne", beanName)) {
				LifecycleUser user = new LifecycleUser();
				user.setName("愆凡V1");

				System.err.println(beanName + " --> postProcessBeforeInstantiation : 返回对象跳过剩余的实例化、初始化操作");

				return user; // 该对象将作为创建的 Bean对象 返回，而不再继续执行 Spring 提供的 Bean 的构建流程
			}
			return null; // 继续执行 Bean 的实例化、初始化操作
		}

		/**
		 * 实例化后会被调用
		 */
		@Override
		public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
			if (ObjectUtils.nullSafeEquals("userOne", beanName)) {
				LifecycleUser user = (LifecycleUser) bean;
				user.setName("愆凡V2");

				System.err.println(beanName + " --> postProcessAfterInstantiation : user.name = " + user.getName());

				return false; // 返回 false 将不允许属性赋值（配置元信息 ——> 属性值）
			}
			return true;
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

			if (ObjectUtils.nullSafeEquals("userOne", beanName) || ObjectUtils.nullSafeEquals("userTwo", beanName)) {
				// 配置 name = 11
				propertyValues.add("id", "9");

				// 修改配置文件中的配置
				PropertyValue namePropertyValue = propertyValues.getPropertyValue("name");
				if (namePropertyValue != null) {
					// 因为 PropertyValue 的 value 属性为 final ，不能修改，所以这里只能先删除再添加
					propertyValues.removePropertyValue("name");
					propertyValues.add("name", "愆凡V3");
				}

				System.err.println(beanName + " --> postProcessProperties : user.name = " + propertyValues.getPropertyValue("name"));
			}

			return propertyValues;
		}

		/* ------------------------------------------ 华丽的分割线 ------------------------------------------ */

		/**
		 * 初始化前会被调用
		 */
		@Override
		public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
			if (ObjectUtils.nullSafeEquals("userOne", beanName) || ObjectUtils.nullSafeEquals("userTwo", beanName)) {
				LifecycleUser user = (LifecycleUser) bean;
				user.setName("愆凡V4");

				System.err.println(beanName + " --> postProcessBeforeInitialization : user.name = " + user.getName());

				return user;
			}
			return bean;
		}

		/**
		 * 初始化后会被调用
		 */
		@Override
		public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
			if (ObjectUtils.nullSafeEquals("userOne", beanName) || ObjectUtils.nullSafeEquals("userTwo", beanName)) {
				LifecycleUser user = (LifecycleUser) bean;
				user.setName("愆凡V5");

				System.err.println(beanName + " --> postProcessAfterInitialization : user.name = " + user.getName());

				return user;
			}
			return bean;
		}

		/* ------------------------------------------ 华丽的分割线 ------------------------------------------ */

		@Override
		public void postProcessBeforeDestruction(Object bean, String beanName) throws BeansException {
			if (ObjectUtils.nullSafeEquals("userFour", beanName)) {
				LifecycleUser user = (LifecycleUser) bean;
				user.setName("愆凡销毁前");

				System.err.println(beanName + " --> postProcessAfterInitialization : user.name = " + user.getName());
			}
		}
	}

}
