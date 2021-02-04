package com.code.spring.ioc.dependency.injection;

import com.code.spring.MySpringApplicationTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.annotation.Resource;

/**
 * 依赖管理：依赖注入示例
 *
 * @author 愆凡
 * @date 2021/1/30 22:57
 */
public class DependencyInjectionFieldTest extends MySpringApplicationTest {

	@Autowired
	public DependencyUserHolder userHolder;

	@Resource
	public DependencyUserHolder userHolderTwo;

	/**
	 * TODO 报错了
	 *
	 * 基于 Autowired 的 Field 注入示例
	 */
	@Test
	public void fieldByAutowiredTest() {
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

		XmlBeanDefinitionReader definitionReader = new XmlBeanDefinitionReader(applicationContext);

		String resoucePath = "classpath:/META-INF/ioc/dependency/injection/dependency-injection-field.xml";
		definitionReader.loadBeanDefinitions(resoucePath);

		applicationContext.register(DependencyInjectionFieldTest.class);

		applicationContext.refresh();

		DependencyInjectionFieldTest dependencyInjection = applicationContext.getBean(DependencyInjectionFieldTest.class);

		DependencyUserHolder userHolder1 = dependencyInjection.userHolder;

		System.err.println(userHolder1);
	}

}
