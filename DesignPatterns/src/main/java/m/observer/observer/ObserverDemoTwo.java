package m.observer.observer;

/**
 * 观察者二
 *
 * @author 愆凡
 * @date 2020/9/9 10:57 上午
 */
public class ObserverDemoTwo implements Observer {

	@Override
	public void update(String message) {
		System.out.println("[ ObserverDemoTwo ] - 收到通知：" + message);
	}

}
