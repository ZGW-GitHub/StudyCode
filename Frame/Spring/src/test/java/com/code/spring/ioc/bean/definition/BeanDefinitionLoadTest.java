package com.code.spring.ioc.bean.definition;

import com.code.spring.MySpringApplicationTest;
import com.code.spring.entity.User;
import org.junit.Test;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;

/**
 * BeanDefinition 读取、解析示例
 *
 * @author 愆凡
 * @date 2020/12/22 20:54
 */
public class BeanDefinitionLoadTest extends MySpringApplicationTest {

	/**
	 * 从 XML 资源中读取解析 BeanDefinition
	 */
	@Test
	public void xmlTest() {
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

		// 实例化基于 xml 资源的 BeanDefinitionReader
		XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);

		// xml 资源的位置
		String file = "/META-INF/bean/definition/beanDefinitionLoad.xml";

		int beanDefinitionsNum = beanDefinitionReader.loadBeanDefinitions(file);
		System.err.println("已加载 BeanDefinition 数量：" + beanDefinitionsNum);

		User user = beanFactory.getBean("user", User.class);
		System.err.println(user);
	}

	/**
	 * 从 Properties 资源中读取解析 BeanDefinition
	 */
	@Test
	public void propertieTest() {
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

		// 实例化基于 Properties 资源的 BeanDefinitionReader
		PropertiesBeanDefinitionReader beanDefinitionReader = new PropertiesBeanDefinitionReader(beanFactory);

		// Properties 资源的位置
		String file = "/META-INF/bean/definition/beanDefinitionLoad.properties";

		// 解决乱码 ( Properties 资源加载默认是通过 ISO-8859-1 编码的 )
		Resource resource = new ClassPathResource(file);
		EncodedResource encodedResource = new EncodedResource(resource, "UTF-8"); // 转换成带有字符编码的 EncodedResource 对象

		// 加载 Properties 资源
		int beanDefinitionsNum = beanDefinitionReader.loadBeanDefinitions(encodedResource);
		System.err.println("已加载 BeanDefinition 数量：" + beanDefinitionsNum);

		User user = beanFactory.getBean("user", User.class);
		System.err.println(user);
	}

	/**
	 * 通过注解读取解析 BeanDefinition
	 */
	@Test
	public void AnnotatedTest() {
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

		// 实例化基于 Java 注解的 AnnotatedBeanDefinitionReader
		AnnotatedBeanDefinitionReader beanDefinitionReader = new AnnotatedBeanDefinitionReader(beanFactory);

		int beanDefinitionCountBeforeRegister = beanFactory.getBeanDefinitionCount();

		// 注册（不要求类必须有 @Component 注解）
		beanDefinitionReader.register(BeanDefinitionLoadTest.class);

		int beanDefinitionCountAfterRegister = beanFactory.getBeanDefinitionCount();
		System.err.println("BeanDefinition Register Count : " + (beanDefinitionCountAfterRegister - beanDefinitionCountBeforeRegister));

		// 普通的 Class 注册到 Spring IoC 容器后，通常 Bean 名称为 Class 的名称且首字母小写
		// Bean 名称的生成来自于 BeanNameGenerator ，注解 Bean 名称的生成来自于 AnnotatedBeanNameGenerator
		BeanDefinitionLoadTest bean = beanFactory.getBean("beanDefinitionLoadTest", BeanDefinitionLoadTest.class);
		System.err.println(bean);
	}

}
