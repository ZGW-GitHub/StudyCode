package com.code.spring.core.bean.definitions;

import com.code.spring.MySpringApplicationTest;
import com.code.spring.entity.User;
import org.junit.Test;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;

public class BeanDefinitionLoadTest extends MySpringApplicationTest {

	@Test
	public void propertieTest() {
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

		// 实例化基于 Properties 资源的 BeanDefinitionReader
		PropertiesBeanDefinitionReader beanDefinitionReader = new PropertiesBeanDefinitionReader(beanFactory);

		// Properties 资源的位置
		String file = "/beanDefinitionLoad.properties";

		// 解决乱码
		Resource resource = new ClassPathResource(file);
		EncodedResource encodedResource = new EncodedResource(resource, "UTF-8");

		int beanDefinitionsNum = beanDefinitionReader.loadBeanDefinitions(encodedResource);
		System.err.println("已加载 BeanDefinition 数量：" + beanDefinitionsNum);

		User user = beanFactory.getBean("user", User.class);
		System.err.println(user);
	}

}
