package com.code.spring.c.ioc.aa.bean.instantiation;

import com.code.spring.c.ioc.aa.bean.definition.SpringBeanDefinitionApplication;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author 愆凡
 * @date 2021/6/21 17:05
 */
@Slf4j
@SpringBootApplication(scanBasePackages = {"com.code.spring.a.basic", "com.code.spring.c.ioc.aa.bean.instantiation"})
public class SpringBeanInstantiationApplication {
	public static void main(String[] args) {

//		test(args);
		// basic : 通过 EntityFactory 实例化
//		userFactory();
		// basic : 通过 构造函数 实例化
//		constructor();
		// basic : 通过 FactoryBean 实例化
//		factoryBean();
		// basic : 通过 FactoryMethod 实例化
//		factoryMethod();

		// other : 通过 AutowireCapableBeanFactory 实例化
		autowireCapableBeanFactory();


	}

	private static void test(String[] args) {
		new SpringApplicationBuilder(SpringBeanInstantiationApplication.class).run(args);
	}

	private static void userFactory() {
		// 启动 Spring
		ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(SpringBeanDefinitionApplication.class).run();

		// 通过 SpringApplication 获取它的 DefaultListableBeanFactory
		DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) applicationContext.getBeanFactory();

		// 实例化基于 xml 资源的 BeanDefinitionReader
		XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);

		// xml 资源的位置
		String filePath = "/files/c/ioc/aa/bean/instantiation/bean-instantiation-bean-factory.xml";

		// 注册 BeanDefinition 到 Ioc 容器
		beanDefinitionReader.loadBeanDefinitions(filePath);

		TestUser user = applicationContext.getBean("user", TestUser.class);
		System.err.println(user);
	}

	private static void constructor() {
		// 启动 Spring
		ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(SpringBeanDefinitionApplication.class).run();

		// 通过 SpringApplication 获取它的 DefaultListableBeanFactory
		DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) applicationContext.getBeanFactory();

		// 实例化基于 xml 资源的 BeanDefinitionReader
		XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);

		// xml 资源的位置
		String filePath = "/files/c/ioc/aa/bean/instantiation/bean-instantiation-constructor.xml";

		// 注册 BeanDefinition 到 Ioc 容器
		beanDefinitionReader.loadBeanDefinitions(filePath);

		TestUser userOne = applicationContext.getBean("userOne", TestUser.class);
		TestUser userTwo = applicationContext.getBean("userTwo", TestUser.class);

		System.err.println(userOne);
		System.err.println(userTwo);
	}

	private static void factoryBean() {
		// 启动 Spring
		ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(SpringBeanDefinitionApplication.class).run();

		// 通过 SpringApplication 获取它的 DefaultListableBeanFactory
		DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) applicationContext.getBeanFactory();

		// 实例化基于 xml 资源的 BeanDefinitionReader
		XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);

		// xml 资源的位置
		String filePath = "/files/c/ioc/aa/bean/instantiation/bean-instantiation-factory-bean.xml";

		// 注册 BeanDefinition 到 Ioc 容器
		beanDefinitionReader.loadBeanDefinitions(filePath);

		TestUser user = applicationContext.getBean("userFactoryBean", TestUser.class);
		System.err.println(user);
	}

	private static void factoryMethod() {
		// 启动 Spring
		ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(SpringBeanDefinitionApplication.class).run();

		// 通过 SpringApplication 获取它的 DefaultListableBeanFactory
		DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) applicationContext.getBeanFactory();

		// 实例化基于 xml 资源的 BeanDefinitionReader
		XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);

		// xml 资源的位置
		String filePath = "/files/c/ioc/aa/bean/instantiation/bean-instantiation-factory-method.xml";

		// 注册 BeanDefinition 到 Ioc 容器
		beanDefinitionReader.loadBeanDefinitions(filePath);

		TestUser user = applicationContext.getBean("user", TestUser.class);
		System.err.println(user);
	}

	private static void autowireCapableBeanFactory() {
		// 启动 Spring
		ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(SpringBeanDefinitionApplication.class).run();

		// 获取 AutowireCapableBeanFactory
		AutowireCapableBeanFactory beanFactory = applicationContext.getAutowireCapableBeanFactory();

		// 创建 Bean
		TestUser user = beanFactory.createBean(TestUser.class);

		System.err.println(user);
	}

}
