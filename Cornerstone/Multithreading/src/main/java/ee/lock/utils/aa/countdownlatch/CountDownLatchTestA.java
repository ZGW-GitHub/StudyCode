package ee.lock.utils.aa.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * API :
 *      await()
 *      countDown()
 */
public class CountDownLatchTestA {

	// 需要等待的线程
	private static final Integer NUM = 5;
    // 线程池（参数：线程个数）
    private static ExecutorService executor = Executors.newFixedThreadPool(2);
    // 线程池（参数：线程可以通过 await 之前必须调用 countDown 的次数）
    private static final CountDownLatch LATCH = new CountDownLatch(NUM);

    public static void main(String[] args) throws InterruptedException {

        System.out.println("main 工作中...");

        // 并行处理数据
        for (int i = 1; i <= NUM; i++) {
            executor.execute(new SimpleRunnable(LATCH, i));
        }

		System.out.println("main 调用了 await");
        // block住，等待计数器为0（等待第一阶段的所有线程和任务完成），或执行该语句（该语句指：latch.await()）的线程被中断
        LATCH.await();

        System.out.println("其它线程完成已完成，main 继续执行...");

        // 异步操作，停止接收任务，线程池中的任务继续有序执行
        executor.shutdown();

    }

    static class SimpleRunnable implements Runnable {

        private final CountDownLatch latch;
        private final Integer i;

        SimpleRunnable(CountDownLatch latch, Integer i) {
            this.latch = latch;
            this.i = i;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " -> " + i + " 工作中...");
			// 计数减一
            latch.countDown();
			System.out.println(Thread.currentThread().getName() + " -> " + i + " 我已经 countDown 了！");
        }

    }


}
