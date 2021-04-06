package com.code.spring.nn.dependency.injection;

import com.code.spring.MySpringApplicationTest;
import org.junit.Test;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 依赖管理：依赖注入示例
 *
 * @author 愆凡
 * @date 2021/1/30 22:57
 */
public class DependencyInjectionConstructorTest extends MySpringApplicationTest {

	/**
	 * 基于 XML 的 Constructor 注入示例
	 */
	@Test
	public void constructorByXmlTest() {
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

		XmlBeanDefinitionReader definitionReader = new XmlBeanDefinitionReader(beanFactory);

		String resoucePath = "classpath:/META-INF/ioc/dependency/injection/dependency-injection-constructor.xml";
		definitionReader.loadBeanDefinitions(resoucePath);

		DependencyUserHolder userHolder = beanFactory.getBean("userHodler", DependencyUserHolder.class);

		System.err.println(userHolder);
	}

	/**
	 * 基于 API 的 Constructor 注入示例
	 */
	@Test
	public void constructorByAnnotationTest() {
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

		XmlBeanDefinitionReader definitionReader = new XmlBeanDefinitionReader(applicationContext);

		String resoucePath = "classpath:/META-INF/ioc/dependency/injection/dependency-injection-constructor.xml";
		definitionReader.loadBeanDefinitions(resoucePath);

		// 构建 BeanDefinition
		BeanDefinitionBuilder definitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(DependencyUserHolder.class);
		definitionBuilder.addConstructorArgReference("user"); // 添加构造器参数值

		// 注册 BeanDefinition
		applicationContext.registerBeanDefinition("userHolderByApi", definitionBuilder.getBeanDefinition());

		applicationContext.refresh();

		DependencyUserHolder userHolder = applicationContext.getBean("userHolderByApi", DependencyUserHolder.class);

		System.err.println(userHolder);
	}

	/**
	 * 基于 Autowire 的 Constructor 注入示例
	 */
	@Test
	public void constructorByAutowireTest() {
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

		XmlBeanDefinitionReader definitionReader = new XmlBeanDefinitionReader(beanFactory);

		String resoucePath = "classpath:/META-INF/ioc/dependency/injection/dependency-injection-constructor.xml";
		definitionReader.loadBeanDefinitions(resoucePath);

		DependencyUserHolder userHolderByName = beanFactory.getBean("userHodlerAutowire", DependencyUserHolder.class);

		System.err.println(userHolderByName);
	}

}
