package O3_Interrupt;

public class Demo3_interrupted {

    private static final Object MONITOR = new Object();

    public static void main(String[] args) throws InterruptedException {

        Thread t = new Thread(() -> {
            synchronized (MONITOR) {
                while (true) {

                }
            }
        });

        t.start();
        Thread.sleep(200);
        System.out.println("1.中断前：" + t.isInterrupted()); // false
        System.out.println("2.中断前：" + Thread.interrupted()); // false
        t.interrupt();

        System.out.println("3.中断后：" + t.isInterrupted()); // true
        System.out.println("5.中断后：" + Thread.interrupted()); // false
        System.out.println("6.Thread.interrupted() 后：" + t.isInterrupted()); // true
        System.out.println("7.中断后：" + Thread.interrupted()); // false
        System.out.println("8.Thread.interrupted() 后：" + t.isInterrupted()); // true

    }

}
