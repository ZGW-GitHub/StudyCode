package com.code.spring.oo.configuration;

import com.code.spring.MySpringApplicationTest;
import org.junit.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.util.ObjectUtils;

/**
 * Spring Bean 元信息 示例：
 * <br/>- 配置元信息
 * <br/>- 属性元信息
 *
 * @author 愆凡
 * @date 2021/1/2 18:03
 */
public class BeanConfigurationTest extends MySpringApplicationTest {

	/**
	 * 测试 Spring Bean 的配置元信息、属性元信息
	 */
	@Test
	@SuppressWarnings("NullableProblems")
	public void beanConfigurationTest() {
		// 定义 BeanDefinition
		BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(ConfigurationUser.class);
		beanDefinitionBuilder.addPropertyValue("name", "愆凡");

		// 获取 AbstractBeanDefinition
		AbstractBeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();

		// 添加 AttributeAccessor （不影响 Bean 的初始化、实例化）
		beanDefinition.setAttribute("name", "愆凡2");

		// 辅助作用，可以用来标识当前 BeanDefinition 的来源
		beanDefinition.setSource("test");

		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

		beanFactory.addBeanPostProcessor(new BeanPostProcessor() {
			@Override
			public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
				if (ObjectUtils.nullSafeEquals("configurationUser", beanName)) {
					BeanDefinition bd = beanFactory.getBeanDefinition(beanName);
					if ("test".equals(bd.getSource())) {
						String name = (String) bd.getAttribute("name");
						ConfigurationUser user = (ConfigurationUser) bean;
						user.setName(name);
					}
				}
				return bean;
			}
		});
	}

}
