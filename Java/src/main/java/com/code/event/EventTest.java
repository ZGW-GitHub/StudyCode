package com.code.event;

import org.junit.Test;

import java.util.EventListener;
import java.util.EventObject;
import java.util.Observable;
import java.util.Observer;

/**
 * 事件监听器示例
 *
 * <br/> 三个角色：
 * <br/> - 观察者(事件监听器)
 * <br/> - 被观察者(事件广播器)
 * <br/> - 消息(事件)
 *
 * @author 愆凡
 * @date 2021/1/19 22:21
 */
@SuppressWarnings("all")
public class EventTest {

	@Test
	public void eventTest() {
		// 创建事件广播器
		EventMulticaster eventMulticaster = new EventMulticaster();
		// 添加事件监听器
		eventMulticaster.addObserver(new EventListenter());
		// 广播事件
		eventMulticaster.notifyObservers(new EventObject("事件发生了"));
	}

	/**
	 * 被观察者，向观察者广播消息
	 */
	class EventMulticaster extends Observable {
		@Override
		public void notifyObservers(Object event) {
			setChanged();
			super.notifyObservers(event); // 广播消息
			clearChanged();
		}
	}

	/**
	 * 观察者，接受广播的消息
	 */
	class EventListenter implements Observer, EventListener {
		@Override
		public void update(Observable eventMulticaster, Object event) {
			// 监听自己感兴趣的事件（当监听多类事件时就是多事件监听、反之则为单事件监听）
			if (event instanceof EventObject) {
				System.err.println("收到了【 " + eventMulticaster.toString() + " 】广播的事件：" + ((EventObject) event).getSource().toString());
			}
		}
	}

}
