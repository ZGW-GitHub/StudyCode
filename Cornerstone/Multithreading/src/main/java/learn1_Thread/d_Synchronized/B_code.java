package learn1_Thread.d_Synchronized;

/**
 * 多线程执行 A_code
 */
public class B_code {

    public static void main(String[] args) {

        final A_code runnable = new A_code();

        Thread t1 = new Thread(runnable, "1");
        Thread t2 = new Thread(runnable, "2");
        Thread t3 = new Thread(runnable, "3");
        t1.start();
        t2.start();
        t3.start();

    }
    
}
