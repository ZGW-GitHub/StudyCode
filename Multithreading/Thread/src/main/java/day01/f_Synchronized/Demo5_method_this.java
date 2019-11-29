package O6_Synchronized;

public class Demo5_method_this {

    public static void main(String[] args) {

        final Demo4_method_this runnable = new Demo4_method_this();

        Thread t1 = new Thread(runnable, "1");
        Thread t2 = new Thread(runnable, "2");
        Thread t3 = new Thread(runnable, "3");
        t1.start();
        t2.start();
        t3.start();

    }

}
