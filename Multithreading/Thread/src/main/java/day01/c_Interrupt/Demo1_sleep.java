package O3_Interrupt;

public class Demo1_sleep {

    public static void main(String[] args) throws InterruptedException {

        Thread t = new Thread(() -> {
                while (true) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        System.out.println(">> 收到中断信号。");
                        e.printStackTrace();
                    }
                    System.out.println("中断之后。。。");
                }
            }
        );

        t.start();
        Thread.sleep(100);
        System.out.println(t.isInterrupted()); // false
        t.interrupt();
        System.out.println(t.isInterrupted()); // true

    }
}
