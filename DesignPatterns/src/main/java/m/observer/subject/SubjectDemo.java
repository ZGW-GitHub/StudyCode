package m.observer.subject;

import m.observer.observer.Observer;

import java.util.ArrayList;
import java.util.List;

/**
 * 被观察者一
 *
 * @author 愆凡
 * @date 2020/9/9 10:51 上午
 */
public class SubjectDemo implements Subject {

	private List<Observer> observers = new ArrayList<>();

	// 注册观察者
	@Override
	public void registerObserver(Observer observer) {
		observers.add(observer);
	}

	// 移除观察者
	@Override
	public void deleteObserver(Observer observer) {
		observers.remove(observer);
	}

	// 通知观察者
	@Override
	public void notifyObservers(String msg) {
		observers.forEach(observer -> observer.update(msg));
	}
}
