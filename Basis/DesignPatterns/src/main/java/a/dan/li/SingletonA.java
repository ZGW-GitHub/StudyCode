package a.dan.li;

/**
 * 懒汉式
 * 只适用于单线程模式
 *
 * @author 愆凡
 */
public class SingletonA {

	// 用于保存单例对象
	private static SingletonA singleton = null;

	private SingletonA() {
	}

	public static SingletonA getInstance() {
		if (singleton == null) {
			singleton = new SingletonA();
		}
		return singleton;
	}

}
