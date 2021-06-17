package com.code.spring.cc.ioc.bean.definition;

import com.code.spring.MySpringApplicationTest;
import com.code.spring.aa.basic.entity.User;
import org.junit.Test;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.*;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;

/**
 * BeanDefinition 构建、解析、注册示例
 *
 * @author 愆凡
 * @date 2020/12/22 20:54
 */
public class BeanDefinitionTest extends MySpringApplicationTest {

	/**
	 * 面向资源 ：XML 资源
	 */
	@Test
	public void byXmlTest() {
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

		// 实例化基于 xml 资源的 BeanDefinitionReader
		XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);

		// xml 资源的位置
		String filePath = "/TEST-FILE/cc/ioc/bean/definition/bean-definition.xml";

		// 注册 BeanDefinition 到 Ioc 容器
		beanDefinitionReader.loadBeanDefinitions(filePath);

		User user = beanFactory.getBean("user", User.class);
		System.err.println(user);
	}

	/**
	 * 面向资源 ：Properties 资源
	 */
	@Test
	public void byPropertieTest() {
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

		// 实例化基于 Properties 资源的 BeanDefinitionReader
		PropertiesBeanDefinitionReader beanDefinitionReader = new PropertiesBeanDefinitionReader(beanFactory);

		// Properties 资源的位置
		String file = "/TEST-FILE/cc/ioc/bean/definition/bean-definition.properties";

		// 解决乱码 ( Properties 资源加载默认是通过 ISO-8859-1 编码的 )
		Resource resource = new ClassPathResource(file);
		EncodedResource encodedResource = new EncodedResource(resource, "UTF-8"); // 转换成带有字符编码的 EncodedResource 对象

		// 注册 BeanDefinition 到 Ioc 容器
		beanDefinitionReader.loadBeanDefinitions(encodedResource);

		User user = beanFactory.getBean("user", User.class);
		System.err.println(user);
	}

	/**
	 * 面向注解
	 */
	@Test
	public void byAnnotatedTest() {
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

		// 实例化基于 Java 注解的 AnnotatedBeanDefinitionReader
		AnnotatedBeanDefinitionReader beanDefinitionReader = new AnnotatedBeanDefinitionReader(beanFactory);

		// 注册（不要求类必须有 @Component 注解）
		beanDefinitionReader.register(BeanDefinitionUser.class);

		beanFactory.getBeanDefinitionCount();

		// 普通的 Class 注册到 Spring IoC 容器后，通常 Bean 名称为 Class 的名称且首字母小写
		// Bean 名称的生成来自于 BeanNameGenerator ，注解 Bean 名称的生成来自于 AnnotatedBeanNameGenerator
		User user = beanFactory.getBean("beanDefinitionUser", BeanDefinitionUser.class);
		System.err.println(user);
	}

	/**
	 * 面向 Api
	 */
	@Test
	public void byApiTest1() {
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

		// 创建 BeanDefinitionBuilder 用于构建 BeanDefinition
		BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(BeanDefinitionUser.class);

		beanDefinitionBuilder.addPropertyValue("id", 1)
				.addPropertyValue("name", "愆凡");

		// 获取 BeanDefinition
		BeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();

		// 注册 BeanDefinition 到 Ioc 容器
		beanFactory.registerBeanDefinition("user", beanDefinition);

		User user = beanFactory.getBean("user", User.class);
		System.err.println(user);
	}

	/**
	 * 面向 Api
	 */
	@Test
	public void byApiTest2() {
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

		// 创建 BeanDefinition 对象
		GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();

		genericBeanDefinition.setBeanClass(BeanDefinitionUser.class);

		MutablePropertyValues propertyValues = new MutablePropertyValues();
		propertyValues
				.add("id", 1)
				.add("name", "愆凡");

		genericBeanDefinition.setPropertyValues(propertyValues);

		// 注册 BeanDefinition 到 Ioc 容器
		BeanDefinitionReaderUtils.registerWithGeneratedName(genericBeanDefinition, beanFactory);

		User user = beanFactory.getBean(User.class);
		System.err.println(user);
	}

}
