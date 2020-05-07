package aa.thread.ee.close;

/**
 * 通过 中断标识位 来终止线程，有 BUG
 *
 * @author NotUpToYou
 */
public class DemoB {

    private static class Worker extends Thread{

        @Override
        public void run() {
            while (true){
                // BUG : 如果线程在这里 阻塞 了，此时不能通过 Boolean 或 中断 标识位来结束线程了，怎么办？看后续的代码
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
