package a.thread.d.synchronize;

/**
 * @author NotUpToYou
 */
public class MethodThisB {

    public static void main(String[] args) {

        final MethodThisA runnable = new MethodThisA();

        Thread t1 = new Thread(runnable, "1");
        Thread t2 = new Thread(runnable, "2");
        Thread t3 = new Thread(runnable, "3");
        t1.start();
        t2.start();
        t3.start();

    }

}
