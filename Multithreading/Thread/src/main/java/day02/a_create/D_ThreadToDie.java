/*
      Date:  2019-08-08 10:43    
                                 */
package a_create;

// 使用中断操作终止一个线程
public class D_ThreadToDie {

    public static void main(String[] args) throws InterruptedException {

        interruptedOne();
        interruptedTwo();

    }

    private static void interruptedOne() throws InterruptedException {

        Thread newThread = new Thread(() -> {
            while (true) {
                if (Thread.interrupted()) {
                    break;
                }
            }
            System.out.println("循环 OVER ！");
        });
        newThread.start();

        Thread.sleep(1_000);

        newThread.interrupt();

    }

    private static void interruptedTwo() throws InterruptedException {

        Thread newThread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(2_000);
                } catch (InterruptedException e) {
                    break;
                }
            }
            System.out.println("循环 OVER ！");
        });
        newThread.start();

        Thread.sleep(1_000);

        newThread.interrupt();

    }

}
