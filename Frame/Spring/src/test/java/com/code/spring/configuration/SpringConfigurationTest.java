package com.code.spring.configuration;

import com.code.spring.MySpringApplicationTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.MapPropertySource;

import java.util.HashMap;
import java.util.Map;

/**
 * Spring 外部化配置示例
 *
 * @author 愆凡
 * @date 2021/1/3 22:43
 */
@PropertySource("classpath:/META-INF/configuration/springConfiguration.properties")
public class SpringConfigurationTest extends MySpringApplicationTest {

	@Bean
	public ConfigurationUser configurationUser(@Value("${user.id}") Long id, @Value("${user.name}") String name) {
		ConfigurationUser user = new ConfigurationUser();
		user.setId(id);
		user.setName(name);
		return user;
	}

	/**
	 * 基于 Java 注解的 Spring 外部化配置测试
	 */
	@Test
	public void AnnotationTest() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

		// 注册当前类作为 Configuration Class
		context.register(SpringConfigurationTest.class);

		// 启动 Spring 应用上下文
		context.refresh();

		// user.name 被 systemProperties 的 user.name 覆盖了
		Map<String, ConfigurationUser> beans = context.getBeansOfType(ConfigurationUser.class);
		beans.forEach((k, v) -> System.err.println("User id : " + v.getId() + "，User name : " + v.getName()));

		System.err.println("应用上下文包含的 PropertySource : " + context.getEnvironment().getPropertySources());

		// 关闭 Spring 应用上下文
		context.close();
	}

	/**
	 * 基于 Java 注解的 Spring 外部化配置测试 ，解决覆盖问题
	 */
	@Test
	public void AnnotationTest2() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

		// 向 Environment 中添加 PropertySource
		// 添加操作必须在 refresh 方法之前完成
		Map<String, Object> propertiesSource = new HashMap<>();
		propertiesSource.put("user.name", "愆凡");
		context.getEnvironment().getPropertySources().addFirst(new MapPropertySource("first-property-source", propertiesSource));

		// 注册当前类作为 Configuration Class
		context.register(SpringConfigurationTest.class);

		// 启动 Spring 应用上下文
		context.refresh();

		Map<String, ConfigurationUser> beans = context.getBeansOfType(ConfigurationUser.class);
		beans.forEach((k, v) -> System.err.println("User id : " + v.getId() + "，User name : " + v.getName()));

		System.err.println("应用上下文包含的 PropertySource : " + context.getEnvironment().getPropertySources());

		// 关闭 Spring 应用上下文
		context.close();
	}

}
