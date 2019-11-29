package O4_ThreadCloseGraceful;

/**
 * 通过一个 Boolean 标识位来终止线程
 */
public class ThreadCloseGraceful {

    private static class Worker extends Thread{

        private volatile boolean flag = true;

        @Override
        public void run() {
            while (flag){
                // 干活。。。
            }
        }

        public void shutdown(){
            this.flag = false;
        }

    };

    public static void main(String[] args) {

        Worker worker = new Worker();
        worker.start();

        try {
            Thread.sleep(3_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        worker.shutdown();
    }

}
