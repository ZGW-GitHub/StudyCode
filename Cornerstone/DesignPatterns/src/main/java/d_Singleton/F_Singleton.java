/*
      Date:  2019-08-08 21:57    
                                 */
package com.snow.sjms.d_Singleton;

// 饿汉式
// 适用于多线程       枚举方式
public enum F_Singleton {

        SINGLE;

        private F_Singleton() {}

        public String thisSingletonName() {
            System.out.println(this);
            return "Hello";
        }

}
