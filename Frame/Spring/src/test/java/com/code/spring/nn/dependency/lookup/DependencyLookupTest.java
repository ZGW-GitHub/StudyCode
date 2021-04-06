package com.code.spring.nn.dependency.lookup;

import com.code.spring.MySpringApplicationTest;
import com.code.spring.nn.dependency.DependencyUser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 依赖管理：依赖查找示例
 *
 * @author 愆凡
 * @date 2021/1/30 18:53
 */
public class DependencyLookupTest extends MySpringApplicationTest {

	private BeanFactory beanFactory;
	private DependencyUser user1;
	private DependencyUser user2;

	/**
	 * 示例：<br />
	 * 通过 Bean 名称查找：实时查找
	 */
	@Test
	public void byNameTest1() {
		user1 = (DependencyUser) beanFactory.getBean("user");
		user2 = (DependencyUser) beanFactory.getBean("user");
	}

	/**
	 * 示例：<br />
	 * 通过 Bean 名称查找：延迟查找
	 */
	@Test
	@SuppressWarnings("all")
	public void byNameTest2() {
		ObjectFactory<DependencyUser> objectFactory =
				(ObjectFactory<DependencyUser>) beanFactory.getBean("objectFactory");

		user1 = objectFactory.getObject();
		user2 = objectFactory.getObject();
	}

	/**
	 * 示例：<br />
	 * 通过 Bean 类型查找：查找单个 Bean 对象
	 */
	@Test
	public void byTypeTest1() {
		user1 = beanFactory.getBean(DependencyUser.class);
		user2 = beanFactory.getBean(DependencyUser.class);
	}

	/**
	 * 示例：<br />
	 * 通过 Bean 类型查找：查找集合 Bean 对象
	 */
	@Test
	public void byTypeTest2() {
		if (beanFactory instanceof ListableBeanFactory) {
			ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
			Map<String, DependencyUser> userMap = listableBeanFactory.getBeansOfType(DependencyUser.class);

			System.err.println(userMap.toString());
		}
	}

	/**
	 * 示例：<br />
	 * 通过 Java 注解查找：查找集合 Bean 对象
	 */
	@Test
	public void byAnnotationTest1() {
		if (beanFactory instanceof ListableBeanFactory) {
			ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
			Map<String, Object> map = listableBeanFactory.getBeansWithAnnotation(Component.class);

			System.err.println(map.toString());
		}
	}

	@Before
	public void before() {
		beanFactory = new ClassPathXmlApplicationContext(
				"classpath:/TEST-FILE/nn/dependency/lookup/dependency-lookup.xml");
	}

	@After
	public void after() {
		if (user1 != null && user2 != null) {
			System.err.println(user1.toString());
			System.err.println(user2.toString());

			System.err.println("两次获取的对象是否相等 ：" + user1.equals(user2));
		}
	}

}
