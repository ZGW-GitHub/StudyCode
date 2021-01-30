package com.code.springs.event;

import com.code.springs.MySpringApplicationTest;
import org.junit.Test;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.ApplicationContextEvent;

/**
 * Spring 层次性上下文的事件传播示例
 *
 * @author 愆凡
 * @date 2021/1/24 18:51
 */
public class HierarchicalEventPropagateTest extends MySpringApplicationTest {

	@Test
	public void test() {
		AnnotationConfigApplicationContext parentContext = new AnnotationConfigApplicationContext();
		parentContext.setId("parent-context");
		parentContext.register(MyApplicationListener.class);

		AnnotationConfigApplicationContext currentContext = new AnnotationConfigApplicationContext();
		currentContext.setId("current-context");
		currentContext.setParent(parentContext);
		currentContext.register(MyApplicationListener.class);

		parentContext.refresh();
		currentContext.refresh();
	}

	static class MyApplicationListener implements ApplicationListener<ApplicationContextEvent> {
		@Override
		public void onApplicationEvent(ApplicationContextEvent event) {
			System.err.printf("Context ID : %s ，收到了事件：%s \n", event.getApplicationContext().getId(), event.getClass().getSimpleName());
		}
	}

}
