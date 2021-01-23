package com.code.spring.ioc.lifecycle;

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
@SuppressWarnings("all")
public class PrepareRefreshTest extends MySpringApplicationTest {

	/**
	 * 示例：<br />
	 * 初始化 Environment 、<br />初始化 Environment 关联的 PropertySource 、<br />设置 Environment 必需的属性 、<br />必需属性的校验 。
	 */
	@Test
	public void initPropertySourcesTest() {
		new TestApplicationContext("com.code.spring.context.lifecycle").start();
	}

	static class TestApplicationContext extends AnnotationConfigApplicationContext {
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
