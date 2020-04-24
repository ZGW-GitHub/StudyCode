package aa.thread.ee.close;

/**
 * 通过一个 Boolean 标识位来终止线程，有 BUG
 *
 * @author NotUpToYou
 */
public class CloseThreadA {

    private static class Worker extends Thread{

        private volatile boolean flag = true;

        @Override
        public void run() {
            // BUG : 如果线程在这里 阻塞 了，此时不能通过 Boolean 或 中断 标识位来结束线程了，怎么办？看后续的代码
            while (flag){
                // 干活。。。
            }
        }

        public void shutdown(){
            this.flag = false;
        }

    };

    public static void main(String[] args) throws InterruptedException {

        Worker worker = new Worker();

        worker.start();

        Thread.sleep(3000);

        worker.shutdown();

    }

}
