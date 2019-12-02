package learn1_Thread.b_Methods;

/**
 * 使用 interrupt() 方法中断 sleep()
 *
 * isInterrupted() 方法：判断线程是否被中断了
 */
public class B_WaitAndInterrupt {

    private static final Object MONITOR = new Object();

    public static void main(String[] args) throws InterruptedException {

        Thread t = new Thread(() -> {
            synchronized (MONITOR) {
                try {
                    MONITOR.wait(3000);
                } catch (InterruptedException e) {
                    System.out.println(">> 收到中断信号。");
                    e.printStackTrace();
                }
                System.out.println("中断之后，可继续做事！");
            }
        });

        t.start();
        Thread.sleep(1000);

        System.out.println(t.isInterrupted()); // false
        t.interrupt();
        System.out.println(t.isInterrupted()); // true

//        t.stop(); // 终止线程，已不建议使用

    }
}
