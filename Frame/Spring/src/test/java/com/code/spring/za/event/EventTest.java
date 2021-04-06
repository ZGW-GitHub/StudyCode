package com.code.spring.za.event;

import com.code.spring.MySpringApplicationTest;
import org.junit.Test;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.support.GenericApplicationContext;

/**
 * 自定义事件示例（从注册自定义监听器到发布自定义的事件）
 *
 * @author 愆凡
 * @date 2021/1/24 19:46
 */
@SuppressWarnings("all")
public class EventTest extends MySpringApplicationTest {

	@Test
	public void test() {
		GenericApplicationContext context = new GenericApplicationContext();

		// 注册自定义事件的监听器
		context.addApplicationListener(new ApplicationListener<MySpringEvent>() {
			@Override
			public void onApplicationEvent(MySpringEvent event) {
				System.err.println(event.toString());
			}
		});

		context.refresh();

		// 发布自定义事件
		context.publishEvent(new MySpringEvent(" Hello "));

		context.close();
	}

	// 自定义 Spring 事件
	class MySpringEvent extends ApplicationEvent {
		public MySpringEvent(Object source) {
			super(source);
		}
	}

}
