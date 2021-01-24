package com.code.spring.event;

import com.code.spring.MySpringApplicationTest;
import org.junit.Test;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.support.GenericApplicationContext;

/**
 * Spring 事件广播器（发布器）示例
 *
 * @see ApplicationEventPublisher
 * @see ApplicationEventPublisherAware
 *
 * @author 愆凡
 * @date 2021/1/23 22:42
 */
@SuppressWarnings("all")
public class EventMulticasterTest extends MySpringApplicationTest implements ApplicationEventPublisherAware {

	/**
	 * Spring 事件发布器示例
	 */
	@Test
	public void publisherTest() {
		GenericApplicationContext context = new GenericApplicationContext();

		// 注册当前类为 Spring Bean 以实现 ApplicationEventPublisher 的注入
		context.registerBean(EventMulticasterTest.class);

		// 注册 Spring 事件监听器
		context.addApplicationListener(new ApplicationListener<ApplicationEvent>() {
			@Override
			public void onApplicationEvent(ApplicationEvent event) {
				System.err.println(event.toString());
			}
		});

		context.refresh();
	}

	// 通过 Spring 调用 set 方法时来发布一个事件
	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher publisher) {
		publisher.publishEvent(new ApplicationEvent("Hello World !") {
		});

		publisher.publishEvent("Hello World !!!");
	}

}
