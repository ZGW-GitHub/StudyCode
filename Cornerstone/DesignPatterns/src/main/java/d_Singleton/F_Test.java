/*
      Date:  2019-08-08 22:00    
                                 */
package com.snow.sjms.d_Singleton;

public class F_Test {

    public static void main(String[] args) {

//        testOneThread();
        testThreads(); // 会发现有几个地址信息不同，说明获取了不同的 A_Singleton 实例对象

    }

    private static void testThreads() {

        for (int i = 0; i < 2000; i++) {
            new Thread(()->{
                F_Singleton singleton = F_Singleton.SINGLE;
                System.out.println(singleton.thisSingletonName());
            }).start();
        }

    }

    private static void testOneThread() {

        F_Singleton instance = F_Singleton.SINGLE;
        F_Singleton instance1 = F_Singleton.SINGLE;

        System.out.println(instance == instance1);

    }

}
