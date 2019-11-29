package O2;

public class ShowV2 implements Runnable {

    private static final int MAX = 500;
    private int index = 1;

    @Override
    public void run() {
        while (index <= MAX)
            System.out.println(Thread.currentThread() + "号柜台：叫了" + (index++) + "号");
    }
}
