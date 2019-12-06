/*
      Date:  2019-08-08 21:57    
                                 */
package d_Singleton;

// 懒汉式
// 只适用于单线程模式
public class A_Singleton {

    private A_Singleton(){}

    private static A_Singleton singleton = null;

    public static A_Singleton getInstance() {
        if (singleton == null) {
            singleton = new A_Singleton();
        }
        return singleton;
    }

}
