package a.dan.li;

/**
 * 饿汉式
 * 适用于多线程       枚举方式
 *
 * @author 愆凡
 */
public enum SingletonF {

	// 用于保存单例对象
	SINGLE;

	private SingletonF() {
	}

	public String thisSingletonName() {
		System.out.println(this);
		return "Hello";
	}

}
