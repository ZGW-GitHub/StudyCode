package day01.f_Synchronized;

/**
 * 多线程执行 Demo1_code
 */
public class Demo2_code {

    public static void main(String[] args) {

        final Demo1_code runnable = new Demo1_code();

        Thread t1 = new Thread(runnable, "1");
        Thread t2 = new Thread(runnable, "2");
        Thread t3 = new Thread(runnable, "3");
        t1.start();
        t2.start();
        t3.start();

    }
    
}
