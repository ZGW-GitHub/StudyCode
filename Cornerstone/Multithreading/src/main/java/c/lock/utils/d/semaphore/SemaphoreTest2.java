package c.lock.utils.d.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * API
 *      availablePermits            可用信号量数量
 *      getQueueLength              阻塞队列长度
 *      acquireUninterruptibly      不可被中断地获取信号量（对中断操作不理睬）
 *      drainPermits                获取所有信号量
 *
 * @author NotUpToYou
 */
public class SemaphoreTest2 {

    private static final Semaphore semaphore = new Semaphore(1);

    public static void main(String[] args) throws InterruptedException {

        for (int i = 1; i <= 3; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println("--------->" + Thread.currentThread().getName() + " : I am start !");
                    TimeUnit.SECONDS.sleep(6);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
            }, String.valueOf(i)).start();
        }

        int i = 60;
        while (i > 0) {
            // 可用信号量数量
            System.out.println(semaphore.availablePermits());
            // 阻塞队列长度
            System.out.println(semaphore.getQueueLength());
            System.out.println("++++++++++++++++++++++++++++++");
            TimeUnit.SECONDS.sleep(1);
            i--;
        }

    }

}
