package n.observer.subject;

import n.observer.observer.Observer;

/**
 * 被观察者接口
 *
 * @author 愆凡
 * @date 2020/9/9 10:45 上午
 */
public interface Subject {

	// 注册观察者
	void registerObserver(Observer observer);

	// 移除观察者
	void deleteObserver(Observer observer);

	// 通知观察者
	void notifyObservers(String msg);

}
