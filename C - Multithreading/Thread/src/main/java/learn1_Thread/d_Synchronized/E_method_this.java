package learn1_Thread.d_Synchronized;

public class E_method_this {

    public static void main(String[] args) {

        final D_method_this runnable = new D_method_this();

        Thread t1 = new Thread(runnable, "1");
        Thread t2 = new Thread(runnable, "2");
        Thread t3 = new Thread(runnable, "3");
        t1.start();
        t2.start();
        t3.start();

    }

}
