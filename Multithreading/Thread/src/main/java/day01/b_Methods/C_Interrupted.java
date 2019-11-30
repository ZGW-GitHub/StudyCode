package day01.b_Methods;

/**
 * Thread 类的静态方法：interrupted()  ：不清除中断标记
 * 与
 * Thread 类的实例方法：isInterrupted() ：清除中断标记
 */
public class C_Interrupted {

    private static final Object MONITOR = new Object();

    public static void main(String[] args) throws InterruptedException {

        Thread t = new Thread(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                System.out.println(">> 收到中断信号。");
                e.printStackTrace();
            }
            System.out.println("中断之后，可继续做事！");
        });

        t.start();
        Thread.sleep(1000);

        System.out.println("中断前：" + t.isInterrupted()); // false : 线程未被中断
        System.out.println("中断前：" + Thread.interrupted()); // false : 线程未被中断，不用清除标记
        t.interrupt();

        // 下面两句输出别同时打开，因为重排序导致它们的执行顺序不一定，从而导致结果不是预期的，可以每次打开一个
        System.out.println("中断后：" + t.isInterrupted()); // true : 线程被中断了返回True
//        System.out.println("中断后：" + Thread.interrupted()); // true : 线程被中断了返回True，并清除标记


    }

}
