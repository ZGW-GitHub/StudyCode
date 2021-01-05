package com.code.spring.context.lifecycle;

import com.code.spring.MySpringApplicationTest;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

/**
 * Spring 应用上下文启动准备阶段：{@link AbstractApplicationContext#prepareRefresh() prepareRefresh() } 。示例：
 *
 * @author 愆凡
 * @date 2021/1/5 11:58
 */
public class PrepareRefreshTest extends MySpringApplicationTest {

	/**
	 * {@link AbstractApplicationContext#initPropertySources() initPropertySources()}
	 * 测试初始化 Environment 、初始化 Environment 关联的 PropertySource 、设置 Environment 必需的属性
	 */
	@Test
	public void initPropertySourcesTest() {
		TestApplicationContext context = new TestApplicationContext("com.code.spring.context.lifecycle");
		context.start();
	}

	class TestApplicationContext extends AnnotationConfigApplicationContext {
		public TestApplicationContext(String... basePackages) {
			super(basePackages);
		}

		// 重写方法，设置 test 为必需的属性
		@Override
		protected void initPropertySources() {
			super.initPropertySources();
			getEnvironment().setRequiredProperties("test");
		}
	}

}
