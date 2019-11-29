package O6_Synchronized;

public class Demo4_method_this implements Runnable {

    private static final int MAX = 50;
    private int index = 1;

    private final Object MONITOR = new Object();

    /*
        // 同步方法
        // 此时的锁是实例对象
        @Override
        public synchronized void run() {
            while (true){
                if (index > MAX)
                    break;
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread() + "号柜台：叫了" + (index++) + "号");
            }
        }
    */

    // 同步方法
    @Override
    public void run() {
        while (true){
            if (flag())
                break;
        }
    }

    private synchronized boolean flag(){
        if (index > MAX)
            return true;
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "号柜台：叫了" + (index++) + "号");
        return false;
    }

}
