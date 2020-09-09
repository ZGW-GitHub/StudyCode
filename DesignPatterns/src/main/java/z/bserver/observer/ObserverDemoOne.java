package z.bserver.observer;

/**
 * 观察者一
 *
 * @author 愆凡
 * @date 2020/9/9 10:56 上午
 */
public class ObserverDemoOne implements Observer {

	@Override
	public void update(String message) {
		System.out.println("[ ObserverDemoOne ] - 收到通知：" + message);
	}

}
