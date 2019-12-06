/*
      Date:  2019-08-08 21:57    
                                 */
package d_Singleton;

// 懒汉式
// 适用于多线程，双重检查加锁，   --->      推荐
public class C_Singleton {

    private C_Singleton(){}

    private static C_Singleton singleton = null;

    // 添加了 synchronized 关键字
    public static C_Singleton getInstance() {
        if (singleton == null) {
            synchronized (C_Singleton.class) {
                if (singleton == null)
                    singleton = new C_Singleton();
            }
        }
        return singleton;
    }

}
