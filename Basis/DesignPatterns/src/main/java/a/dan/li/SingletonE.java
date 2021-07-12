package a.dan.li;

/**
 * 饿汉式
 * 适用于多线程
 *
 * @author 愆凡
 */
public class SingletonE {

	private SingletonE() {
	}

	private static final SingletonE SINGLETON = new SingletonE();

	public static SingletonE getInstance() {
		return SINGLETON;
	}

}
