package ee.lock.utils.nn.semaphore;

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
					TimeUnit.SECONDS.sleep(2);
					System.out.println("--------------->" + Thread.currentThread().getName() + " : start !");
					// 获取信号量
					semaphore.acquire();
                    TimeUnit.SECONDS.sleep(6);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                	// 释放信号量
                    semaphore.release();
					System.out.println("--------------->" + Thread.currentThread().getName() + " : over !");
                }
            }, "Thread " + i).start();
        }

        int i = 20;
        while (i > 0) {
            // 可用信号量数量
            System.out.println("可用信号量数量：" + semaphore.availablePermits());
            // 阻塞队列长度
            System.out.println("阻塞队列长度：" + semaphore.getQueueLength());
            System.out.println("++++++++++++++++++++++++++++++");
            TimeUnit.SECONDS.sleep(1);
            i--;
        }

    }

}
