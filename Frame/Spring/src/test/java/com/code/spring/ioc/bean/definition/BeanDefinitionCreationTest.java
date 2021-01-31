package com.code.spring.ioc.bean.definition;

import com.code.spring.MySpringApplicationTest;
import com.code.spring.ioc.bean.BeanUser;
import org.junit.Test;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.GenericBeanDefinition;

/**
 * BeanDefinition 构建示例
 *
 * @author 愆凡
 * @date 2021/1/31 20:25
 */
public class BeanDefinitionCreationTest extends MySpringApplicationTest {

	/**
	 * 通过 Api 构建 BeanDefinition ：BeanDefinitionBuilder
	 */
	@Test
	public void byApiTest1() {
		BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(BeanUser.class);

		beanDefinitionBuilder.addPropertyValue("id", 1)
				.addPropertyValue("name", "愆凡");

		BeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();

		System.err.println(beanDefinition.toString());
	}

	/**
	 * 通过 Api 构建 BeanDefinition ：AbstractBeanDefinition 以及派生类
	 */
	@Test
	public void byApiTest2() {
		GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();

		genericBeanDefinition.setBeanClass(BeanUser.class);

		MutablePropertyValues propertyValues = new MutablePropertyValues();
		propertyValues
				.add("id", 1)
				.add("name", "愆凡");

		genericBeanDefinition.setPropertyValues(propertyValues);

		System.err.println(genericBeanDefinition.toString());
	}

}
