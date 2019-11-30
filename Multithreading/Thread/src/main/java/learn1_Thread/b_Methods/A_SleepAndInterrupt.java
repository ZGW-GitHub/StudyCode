package learn1_Thread.b_Methods;

/**
 * 使用 interrupt() 方法中断 sleep()
 *
 * isInterrupted() 方法：判断线程是否被中断了
 */
public class A_SleepAndInterrupt {

    public static void main(String[] args) throws InterruptedException {

        Thread t = new Thread(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                System.out.println(">> 收到中断信号。");
//                e.printStackTrace();
            }
            System.out.println("中断之后，可继续做事！");
        });

        t.start();
        Thread.sleep(1000);

        System.out.println(t.isInterrupted()); // false
        t.interrupt();
        System.out.println(t.isInterrupted()); // true

    }
}
