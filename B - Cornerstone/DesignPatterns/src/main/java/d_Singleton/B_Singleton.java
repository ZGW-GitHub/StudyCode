/*
      Date:  2019-08-08 21:57    
                                 */
package d_Singleton;

// 懒汉式
// 适用于多线程，但效率不高
public class B_Singleton {

    private B_Singleton(){}

    private static B_Singleton singleton = null;

    // 添加了 synchronized 关键字
    public static synchronized B_Singleton getInstance() {
        if (singleton == null) {
            singleton = new B_Singleton();
        }
        return singleton;
    }

}
