/*
      Date:  2019-08-08 10:51    
                                 */
package b_run;

public class A_RunByOrder {

    public static void main(String[] args) throws InterruptedException {

//        one();
        System.out.println("-------------------");
        two();

    }

    public static void one() throws InterruptedException {

        Thread t1 = new Thread(() -> {
            System.out.println("Thread one ！");
        });
        Thread t2 = new Thread(() -> {
            System.out.println("Thread two ！");
        });
        Thread t3 = new Thread(() -> {
            System.out.println("Thread three ！");
        });

        t1.start();
        t1.join();
        t2.start();
        t2.join();
        t3.start();
        t3.join();

    }

    public static void two() throws InterruptedException {

        Thread t1 = new Thread(() -> {
            System.out.println("Thread one ！");
        });
        Thread t2 = new Thread(() -> {
            System.out.println("Thread two ！");
        });
        Thread t3 = new Thread(() -> {
            System.out.println("Thread three ！");
        });

        t1.start();
        while (t1.isAlive()) {
            // 自旋
            Thread.sleep(0);
        }
        t2.start();
        while (t1.isAlive()) {
            // 自旋
            Thread.sleep(0);
        }
        t3.start();
        while (t1.isAlive()) {
            // 自旋
            Thread.sleep(0);
        }

    }

}
