package z.bserver;

import z.bserver.observer.ObserverDemoOne;
import z.bserver.subject.SubjectDemo;

/**
 * @author 愆凡
 * @date 2020/9/9 10:58 上午
 */
public class Test {
	public static void main(String[] args) {

		// 创建被观察者
		SubjectDemo subjectDemo = new SubjectDemo();

		// 注册观察者
		subjectDemo.registerObserver(new ObserverDemoOne());
		subjectDemo.registerObserver(new ObserverDemoOne());

		// 通知观察者
		subjectDemo.notifyObservers("test");

	}
}
