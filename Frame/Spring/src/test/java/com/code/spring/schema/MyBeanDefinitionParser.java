package com.code.spring.schema;

import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

/**
 * @author 愆凡
 * @date 2021/2/3 10:45
 */
public class MyBeanDefinitionParser implements BeanDefinitionParser {

	private final Class<?> beanClass;

	public MyBeanDefinitionParser(Class<?> beanClass) {
		this.beanClass = beanClass;
	}

	private static BeanDefinition parse(Element element, ParserContext parserContext, Class<?> beanClass) {
		if (!MyApplicationConfig.class.equals(beanClass)) {
			return null;
		}

		RootBeanDefinition beanDefinition = new RootBeanDefinition();

		beanDefinition.setBeanClass(beanClass);
		beanDefinition.setLazyInit(false);
		MutablePropertyValues propertyValues = new MutablePropertyValues();
		propertyValues.add("appName", element.getAttribute("appName"))
				.add("address", element.getAttribute("address"))
				.add("port", element.getAttribute("port"));

		beanDefinition.setPropertyValues(propertyValues);

		parserContext.getRegistry().registerBeanDefinition("myApplicationConfig", beanDefinition);

		return beanDefinition;
	}

	@Override
	@SuppressWarnings("All")
	public BeanDefinition parse(Element element, ParserContext parserContext) {
		return parse(element, parserContext, beanClass);
	}

}
