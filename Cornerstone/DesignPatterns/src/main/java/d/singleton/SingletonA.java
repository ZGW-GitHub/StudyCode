/*
      Date:  2019-08-08 21:57
                                 */
package d.singleton;

/**
 * 懒汉式
 * 只适用于单线程模式
 * @author 愆凡
 */
public class SingletonA {

    /**
     * 构造函数私有化
     */
    private SingletonA(){}

    /**
     * 用于保存单例对象
     */
    private static SingletonA singleton = null;

    public static SingletonA getInstance() {
        if (singleton == null) {
            singleton = new SingletonA();
        }
        return singleton;
    }

}
