package com.code.spring.c.ioc.aa.bean.lifecycle;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;

/**
 * @author 愆凡
 * @date 2021/6/18 15:47
 */
@Slf4j
@Setter
@SpringBootApplication(scanBasePackages = {"com.code.spring.a.basic", "com.code.spring.c.ioc.aa.bean.lifecycle"})
public class SpringBeanLifecycleApplication implements BeanNameAware, BeanFactoryAware, BeanClassLoaderAware, EnvironmentAware {

	private String beanName;
	private ClassLoader beanClassLoader;
	private BeanFactory beanFactory;
	private Environment environment;

	public static void main(String[] args) {

		test(args);


	}

	private static void test(String[] args) {
		new SpringApplicationBuilder(SpringBeanLifecycleApplication.class).run(args);
	}


	/**
	 * 测试 Bean 的实例化、初始化阶段调用的 PostProcess 方法（这里会跳过 Bean 部分的实例化、初始化阶段）
	 */
	@Test
	public void beanPostProcessTestOne() {
		// 启动 Spring
		ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(SpringBeanLifecycleApplication.class).run();

		// 通过 SpringApplication 获取它的 DefaultListableBeanFactory
		DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) applicationContext.getBeanFactory();

		// 实例化基于 Properties 资源的 BeanDefinitionReader
		PropertiesBeanDefinitionReader beanDefinitionReader = new PropertiesBeanDefinitionReader(beanFactory);

		// Properties 资源的位置
		String file = "/files/c/ioc/aa/bean/lifecycle/bean-definition.properties";

		// 解决乱码 ( Properties 资源加载默认是通过 ISO-8859-1 编码的 )
		Resource resource = new ClassPathResource(file);
		EncodedResource encodedResource = new EncodedResource(resource, "UTF-8"); // 转换成带有字符编码的 EncodedResource 对象

		// 注册 BeanDefinition 到 IoC 容器
		beanDefinitionReader.loadBeanDefinitions(encodedResource);

		BeanUser user = beanFactory.getBean("userOne", BeanUser.class);
		System.err.println(user);
	}

	/**
	 * 测试 Bean 的实例化、初始化阶段调用的 PostProcess 方法
	 */
	@Test
	public void beanPostProcessTestTwo() {
		// 启动 Spring
		ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(SpringBeanLifecycleApplication.class).run();

		// 通过 SpringApplication 获取它的 DefaultListableBeanFactory
		DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) applicationContext.getBeanFactory();

		// 实例化基于 Properties 资源的 BeanDefinitionReader
		PropertiesBeanDefinitionReader beanDefinitionReader = new PropertiesBeanDefinitionReader(beanFactory);

		// Properties 资源的位置
		String file = "/files/c/ioc/aa/bean/lifecycle/bean-definition.properties";

		// 解决乱码 ( Properties 资源加载默认是通过 ISO-8859-1 编码的 )
		Resource resource = new ClassPathResource(file);
		EncodedResource encodedResource = new EncodedResource(resource, "UTF-8"); // 转换成带有字符编码的 EncodedResource 对象

		// 注册 BeanDefinition 到 IoC 容器
		beanDefinitionReader.loadBeanDefinitions(encodedResource);

		BeanUser user = beanFactory.getBean("userTwo", BeanUser.class);
		System.err.println(user);
	}

	/**
	 * 测试 Bean 的初始化（ @PostConstruct 、afterPropertiesSet() 、init() ）
	 */
	@Test
	public void beanPostProcessTestThree() {
		// 启动 Spring
		ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(SpringBeanLifecycleApplication.class).run();

		// 通过 SpringApplication 获取它的 DefaultListableBeanFactory
		DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) applicationContext.getBeanFactory();

		// 解决 @PostConstruct 方法无法回调的问题
		beanFactory.addBeanPostProcessor(new CommonAnnotationBeanPostProcessor());

		// 实例化基于 XML 资源的 BeanDefinitionReader
		XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);

		// XML 资源的位置
		String file = "/files/c/ioc/aa/bean/lifecycle/bean-definition.xml";

		// 注册 BeanDefinition 到 IoC 容器
		beanDefinitionReader.loadBeanDefinitions(file);

		BeanUser user = beanFactory.getBean("userThree", BeanUser.class);
		System.err.println(user);
	}

	/**
	 * 测试 Bean 的销毁（ @PreDestroy 、destroy() 、destroyDemo() ）
	 */
	@Test
	public void beanPostProcessTestFour() {
		// 启动 Spring
		ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(SpringBeanLifecycleApplication.class).run();

		// 通过 SpringApplication 获取它的 DefaultListableBeanFactory
		DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) applicationContext.getBeanFactory();

		// 解决 @PostConstruct 方法无法回调的问题
		beanFactory.addBeanPostProcessor(new CommonAnnotationBeanPostProcessor());

		// 实例化基于 XML 资源的 BeanDefinitionReader
		XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);

		// XML 资源的位置
		String file = "/files/c/ioc/aa/bean/lifecycle/bean-definition.xml";

		// 注册 BeanDefinition 到 IoC 容器
		beanDefinitionReader.loadBeanDefinitions(file);

		BeanUser user = beanFactory.getBean("userFour", BeanUser.class);
		System.err.println(user);

		beanFactory.destroyBean(user); // 销毁 Bean ，并不代表 Bean 被 GC 了
	}

}
