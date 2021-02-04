package com.code.spring.ioc.dependency.injection;

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
public class DependencyInjectionTest extends MySpringApplicationTest {

	/**
	 * 基于 XML 的 Setter 注入示例
	 */
	@Test
	public void setterByXmlTest() {
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

		XmlBeanDefinitionReader definitionReader = new XmlBeanDefinitionReader(beanFactory);

		String resoucePath = "classpath:/META-INF/ioc/dependency/injection/dependency-injection.xml";
		definitionReader.loadBeanDefinitions(resoucePath);

		DependencyUserHolder userHolder = beanFactory.getBean(DependencyUserHolder.class);

		System.err.println(userHolder);
	}

	/**
	 * 基于 API 的 Setter 注入示例
	 */
	@Test
	public void setterByAnnotationTest() {
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

		XmlBeanDefinitionReader definitionReader = new XmlBeanDefinitionReader(applicationContext);

		String resoucePath = "classpath:/META-INF/ioc/dependency/injection/dependency-injection.xml";
		definitionReader.loadBeanDefinitions(resoucePath);

		// 构建 BeanDefinition
		BeanDefinitionBuilder definitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(DependencyUserHolder.class);
		definitionBuilder.addPropertyReference("user", "user");

		// 注册 BeanDefinition
		applicationContext.registerBeanDefinition("userHolderByApi", definitionBuilder.getBeanDefinition());

		applicationContext.refresh();

		DependencyUserHolder userHolder = applicationContext.getBean("userHolderByApi", DependencyUserHolder.class);

		System.err.println(userHolder);
	}

}
