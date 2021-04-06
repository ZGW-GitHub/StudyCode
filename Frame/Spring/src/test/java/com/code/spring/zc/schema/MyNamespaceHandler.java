package com.code.spring.zc.schema;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * @author 愆凡
 * @date 2021/2/3 10:46
 */
public class MyNamespaceHandler extends NamespaceHandlerSupport {

	@Override
	public void init() {
		super.registerBeanDefinitionParser("applicationConfig", new MyBeanDefinitionParser(MyApplicationConfig.class));
	}

}
