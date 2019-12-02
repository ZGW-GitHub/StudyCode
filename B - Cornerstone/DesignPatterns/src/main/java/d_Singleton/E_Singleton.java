/*
      Date:  2019-08-08 21:57    
                                 */
package com.snow.sjms.d_Singleton;

// 饿汉式
// 适用于多线程
public class E_Singleton {

    private static final E_Singleton singleton = new E_Singleton();

    private E_Singleton(){}

    public static E_Singleton getInstance() {
        return singleton;
    }

}
