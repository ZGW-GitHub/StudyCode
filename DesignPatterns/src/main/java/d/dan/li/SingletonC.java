package d.dan.li;

/**
 * 懒汉式
 * 适用于多线程,双重检查加锁.   --->      推荐
 *
 * @author 愆凡
 */
public class SingletonC {

    private SingletonC(){}

    private static SingletonC singleton = null;

    // 添加了 synchronized 关键字，并进行双重检查
    public static SingletonC getInstance() {
        if (singleton == null) {
            synchronized (SingletonC.class) {
                if (singleton == null) {
                    singleton = new SingletonC();
                }
            }
        }
        return singleton;
    }

}
