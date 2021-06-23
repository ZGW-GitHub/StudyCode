package com.code.spring.c.ioc.bean.definition;

import com.code.spring.a.basic.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.*;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;

/**
 * BeanDefinition 构建、解析、注册示例
 *
 * @author 愆凡
 * @date 2021/6/21 17:05
 */
@Slf4j
@SpringBootApplication(scanBasePackages = {"com.code.spring.a.basic", "com.code.spring.c.ioc.bean.definition"})
public class SpringBeanDefinitionApplication {
	public static void main(String[] args) {

//		test(args);

		// 面向资源加载 BeanDefinition ：XML 资源
//		byXmlTest(); 
		// 面向资源加载 BeanDefinition ：Properties 资源
//		byPropertieTest(); 
		// 面向注解
//		byAnnotatedTest();
		// 面向 Api
//		byApiTest1();
		byApiTest2();

	}

	private static void test(String[] args) {
		new SpringApplicationBuilder(SpringBeanDefinitionApplication.class).run(args);
	}


	/**
	 * 面向资源 ：XML 资源
	 */
	private static void byXmlTest() {
		// 启动 Spring
		ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(SpringBeanDefinitionApplication.class).run();

		// 通过 SpringApplication 获取它的 DefaultListableBeanFactory
		DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) applicationContext.getBeanFactory();

		// 实例化基于 xml 资源的 BeanDefinitionReader
		XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);

		// xml 资源的位置
		String filePath = "/files/c/ioc/bean/definition/bean-definition.xml";

		// 注册 BeanDefinition 到 IoC 容器
		beanDefinitionReader.loadBeanDefinitions(filePath);

		User user = applicationContext.getBean("user", User.class);
		System.err.println(user);
	}

	/**
	 * 面向资源 ：Properties 资源
	 */
	private static void byPropertieTest() {
		// 启动 Spring
		ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(SpringBeanDefinitionApplication.class).run();

		// 通过 SpringApplication 获取它的 DefaultListableBeanFactory
		DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) applicationContext.getBeanFactory();

		// 实例化基于 Properties 资源的 BeanDefinitionReader
		PropertiesBeanDefinitionReader beanDefinitionReader = new PropertiesBeanDefinitionReader(beanFactory);

		// Properties 资源的位置
		String file = "/files/c/ioc/bean/definition/bean-definition.properties";

		// 解决乱码 ( Properties 资源加载默认是通过 ISO-8859-1 编码的 )
		Resource resource = new ClassPathResource(file);
		EncodedResource encodedResource = new EncodedResource(resource, "UTF-8"); // 转换成带有字符编码的 EncodedResource 对象

		// 注册 BeanDefinition 到 IoC 容器
		beanDefinitionReader.loadBeanDefinitions(encodedResource);

		User user = beanFactory.getBean("user", User.class);
		System.err.println(user);
	}

	/**
	 * 面向注解
	 */
	private static void byAnnotatedTest() {
		// 启动 Spring
		ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(SpringBeanDefinitionApplication.class).run();

		// 通过 SpringApplication 获取它的 DefaultListableBeanFactory
		DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) applicationContext.getBeanFactory();

		// 实例化基于 Java 注解的 AnnotatedBeanDefinitionReader
		AnnotatedBeanDefinitionReader beanDefinitionReader = new AnnotatedBeanDefinitionReader(beanFactory);

		// 注册（不要求类必须有 @Component 注解）
		beanDefinitionReader.register(TestUser.class);

		// 普通的 Class 注册到 Spring IoC 容器后，通常 Bean 名称为 Class 的名称且首字母小写
		// Bean 名称的生成来自于 BeanNameGenerator ，注解 Bean 名称的生成来自于 AnnotatedBeanNameGenerator
		User user = beanFactory.getBean("beanDefinitionUser", TestUser.class);
		System.err.println(user);
	}

	/**
	 * 面向 Api
	 */
	private static void byApiTest1() {
		// 启动 Spring
		ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(SpringBeanDefinitionApplication.class).run();

		// 通过 SpringApplication 获取它的 DefaultListableBeanFactory
		DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) applicationContext.getBeanFactory();

		// 创建 BeanDefinitionBuilder 用于构建 BeanDefinition
		BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(TestUser.class);

		beanDefinitionBuilder
				.addPropertyValue("id", 1)
				.addPropertyValue("name", "愆凡");

		// 获取 BeanDefinition
		BeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();

		// 注册 BeanDefinition 到 IoC 容器
		beanFactory.registerBeanDefinition("user", beanDefinition);

		User user = beanFactory.getBean("user", User.class);
		System.err.println(user);
	}

	/**
	 * 面向 Api
	 */
	private static void byApiTest2() {
		// 启动 Spring
		ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(SpringBeanDefinitionApplication.class).run();

		// 通过 SpringApplication 获取它的 DefaultListableBeanFactory
		DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) applicationContext.getBeanFactory();

		// 创建 BeanDefinition
		GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();
		genericBeanDefinition.setBeanClass(TestUser.class);
		// 创建 MutablePropertyValues
		MutablePropertyValues propertyValues = new MutablePropertyValues();
		propertyValues
				.add("id", 1)
				.add("name", "愆凡");
		// 关联 BeanDefinition 、MutablePropertyValues
		genericBeanDefinition.setPropertyValues(propertyValues);

		// 注册 BeanDefinition 到 IoC 容器
		BeanDefinitionReaderUtils.registerWithGeneratedName(genericBeanDefinition, beanFactory);

		User user = beanFactory.getBean(User.class);
		System.err.println(user);
	}

}
