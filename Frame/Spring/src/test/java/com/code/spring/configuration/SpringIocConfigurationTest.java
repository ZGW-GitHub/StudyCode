package com.code.spring.configuration;

import com.code.spring.MySpringApplicationTest;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

import java.util.Map;

/**
 * Spring IoC 容器元信息配置示例
 *
 * @author 愆凡
 * @date 2021/1/3 22:05
 */
@ImportResource("classpath:/META-INF/configuration/spring-ioc-configuration.xml")
@Import(ConfigurationUser.class)
public class SpringIocConfigurationTest extends MySpringApplicationTest {

	/**
	 * 基于 Java 注解的 Spring IoC 容器元信息配置测试
	 */
	@Test
	public void AnnotationTest() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

		// 注册当前类作为 Configuration Class
		context.register(SpringIocConfigurationTest.class);

		// 启动 Spring 应用上下文
		context.refresh();

		Map<String, ConfigurationUser> beans = context.getBeansOfType(ConfigurationUser.class);
		beans.forEach((k, v) -> System.err.println("User id : " + v.getId() + "，User name : " + v.getName()));

		// 关闭 Spring 应用上下文
		context.close();
	}

}
