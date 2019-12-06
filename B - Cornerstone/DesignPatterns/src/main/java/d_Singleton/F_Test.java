/*
      Date:  2019-08-08 22:00    
                                 */
package d_Singleton;

public class F_Test {

    public static void main(String[] args) {

        testOneThread();
//        testThreads();

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
