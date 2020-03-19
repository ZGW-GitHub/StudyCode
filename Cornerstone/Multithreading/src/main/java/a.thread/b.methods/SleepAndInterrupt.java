package a.thread.b.methods;

/**
 * 使用 interrupt() 方法中断 sleep()
 * <p>
 * isInterrupted() 方法：判断线程是否被中断了
 *
 * @author NotUpToYou
 */
public class SleepAndInterrupt {

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

        // false
        System.out.println(t.isInterrupted());
        t.interrupt();

        // true
        System.out.println(t.isInterrupted());

    }
}
