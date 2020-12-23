package com.code.spring.core.bean.lifecycle;

import com.code.spring.MySpringApplicationTest;
import com.code.spring.entity.User;
import org.junit.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.util.ObjectUtils;

/**
 * Bean 实例化示例
 *
 * @author 愆凡
 * @date 2020/12/23 22:41
 */
public class BeanInstantiationTest extends MySpringApplicationTest {

	/**
	 * 测试 BeanPostProcess
	 */
	@Test
	public void beanPostProcessTest() {
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

		// 添加 BeanPostProcessor
		beanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());

		// 实例化基于 Properties 资源的 BeanDefinitionReader
		PropertiesBeanDefinitionReader beanDefinitionReader = new PropertiesBeanDefinitionReader(beanFactory);

		// Properties 资源的位置
		String file = "/META-INF/beanDefinitionLoad.properties";

		// 解决乱码
		Resource resource = new ClassPathResource(file);
		EncodedResource encodedResource = new EncodedResource(resource, "UTF-8");

		// 加载 Properties 资源
		int beanDefinitionsNum = beanDefinitionReader.loadBeanDefinitions(encodedResource);
		System.err.println("已加载 BeanDefinition 数量：" + beanDefinitionsNum);

		User user = beanFactory.getBean("user", User.class);
		System.err.println(user);
	}

	/**
	 * 自定义 BeanPostProcess
	 */
	static class MyInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {

		@Override
		public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
			if (ObjectUtils.nullSafeEquals("user", beanName)) {
				// 该对象将作为 createBean() 的结果返回，而不再执行 doCreateBean() 实例化对象
				return new User();
			}
			// 继续执行 Spring IoC 容器的实例化操作
			return null;
		}

	}

}
