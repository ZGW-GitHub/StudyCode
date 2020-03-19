package a.thread.d.synchronize;

/**
 * 多线程执行 CodeA
 *
 * @author NotUpToYou
 */
public class CodeB {

    public static void main(String[] args) {

        final CodeA runnable = new CodeA();

        Thread t1 = new Thread(runnable, "1");
        Thread t2 = new Thread(runnable, "2");
        Thread t3 = new Thread(runnable, "3");
        t1.start();
        t2.start();
        t3.start();

    }

}
