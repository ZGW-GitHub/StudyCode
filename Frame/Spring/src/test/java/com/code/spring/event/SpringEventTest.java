package com.code.spring.event;

import com.code.spring.MySpringApplicationTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.EventListener;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * Spring 事件示例
 *
 * @see ApplicationListener
 * @see ApplicationEvent
 * @see
 *
 * @author 愆凡
 * @date 2021/1/23 20:30
 */
@EnableAsync // 激活异步执行
@Slf4j
@SuppressWarnings("all")
public class SpringEventTest extends MySpringApplicationTest {

	/**
	 * 基于接口的事件监听示例
	 */
	@Test
	public void interfaceTest() {
		GenericApplicationContext context = new GenericApplicationContext();

		// 添加事件监听器
		context.addApplicationListener(new ApplicationListener<ApplicationEvent>() {
			@Override
			public void onApplicationEvent(ApplicationEvent event) {
				System.err.println(event.toString());
			}
		});

		context.refresh();
	}

	/**
	 * 基于注解的事件监听示例
	 */
	@Test
	public void annotationTest() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

		context.register(SpringEventTest.class);

		context.refresh();
	}

	@Async // 异步执行
	@EventListener
	public void onApplicationEvent(ApplicationEvent event) {
		System.err.println(event.toString());
	}

}
