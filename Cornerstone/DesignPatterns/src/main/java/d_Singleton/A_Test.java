/*
      Date:  2019-08-08 22:00    
                                 */
package com.snow.sjms.d_Singleton;

public class A_Test {

    public static void main(String[] args) {

//        testOneThread();
        testThreads(); // 会发现有几个地址信息不同，说明获取了不同的 A_Singleton 实例对象

    }

    private static void testThreads() {

        for (int i = 0; i < 2000; i++) {
            new Thread(()->{
                A_Singleton instance = A_Singleton.getInstance();
                System.out.println(instance);
            }).start();
        }

    }

    private static void testOneThread() {

        A_Singleton instance = A_Singleton.getInstance();
        A_Singleton instance1 = A_Singleton.getInstance();

        System.out.println(instance == instance1);

    }

}
