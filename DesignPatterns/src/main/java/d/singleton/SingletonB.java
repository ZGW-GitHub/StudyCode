/*
      Date:  2019-08-08 21:57
                                 */
package d.singleton;

/**
 * 懒汉式
 * 适用于多线程，但效率不高
 * @author 愆凡
 */
public class SingletonB {

    private SingletonB(){}

    private static SingletonB singleton = null;

    /**
     * 添加了 synchronized 关键字,进行同步
     * @return
     */
    public static synchronized SingletonB getInstance() {
        if (singleton == null) {
            singleton = new SingletonB();
        }
        return singleton;
    }

}
