/*
      Date:  2019-08-08 21:57
                                 */
package d.singleton;

/**
 * 饿汉式
 * 适用于多线程       枚举方式
 * @author 愆凡
 */
public enum SingletonF {

    /**
     * 用于保存单例对象
     */
    SINGLE;

    private SingletonF() {
    }

    public String thisSingletonName() {
        System.out.println(this);
        return "Hello";
    }

}
