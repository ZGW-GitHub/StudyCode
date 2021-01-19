package com.code.event;

import org.junit.Test;

import java.util.Observable;
import java.util.Observer;

/**
 * 观察者模式示例
 *
 * <br/> 三个角色：
 * <br/> - 观察者
 * <br/> - 被观察者
 * <br/> - 消息
 *
 * @author 愆凡
 * @date 2021/1/19 22:21
 */
@SuppressWarnings("all")
public class ObserverTest {

	@Test
	public void observerTest() {
		// 创建被观察者
		ObservableDemo observable = new ObservableDemo();
		// 添加观察者
		observable.addObserver(new ObserverDemo());
		// 广播消息
		observable.notifyObservers("This is msg");
	}

	/**
	 * 被观察者，向观察者广播消息
	 */
	class ObservableDemo extends Observable {
		@Override
		public void notifyObservers(Object msg) {
			setChanged();
			super.notifyObservers(msg); // 广播消息
			clearChanged();
		}
	}

	/**
	 * 观察者，接受广播的消息
	 */
	class ObserverDemo implements Observer {
		@Override
		public void update(Observable observable, Object msg) {
			System.err.println("收到了【 " + observable.toString() + " 】广播的消息：" + msg.toString());
		}
	}


}
