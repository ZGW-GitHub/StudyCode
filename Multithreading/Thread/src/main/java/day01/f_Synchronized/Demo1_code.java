package day01.f_Synchronized;

/**
 * 定义 Runnable
 *
 * 同步代码块的使用
 */
public class Demo1_code implements Runnable {

    private static final int MAX = 50;
    private int index = 1;

    private final Object MONITOR = new Object();

    @Override
    public void run() {
        while (true){
            // 同步代码块
            synchronized (MONITOR){
                if (index > MAX) {
                    break;
                }
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread() + "号柜台：叫了" + (index++) + "号");
            }
        }
    }

}
