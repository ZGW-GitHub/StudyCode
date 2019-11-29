package O4_ThreadCloseGraceful;

/**
 * 通过一个操作 中断标识位 来终止线程
 */
public class ThreadCloseGraceful2 {

    private static class Worker extends Thread{

        @Override
        public void run() {
            while (true){
                if (Thread.interrupted()){
                    break;
                }
                // 干活
            }
            // 善后：当被中断可以跳出循环，就能执行“善后”工作了 。
        }

    };

    public static void main(String[] args) {

        Worker worker = new Worker();
        worker.start();

        try {
            Thread.sleep(1_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        worker.interrupt();

    }

}
