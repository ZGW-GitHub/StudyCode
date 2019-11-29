package com.a_snow.b_utils.v1_CountDownLatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchTest {

    // 线程池（参数：线程个数）
    private static ExecutorService executor = Executors.newFixedThreadPool(2);
    // 线程池（参数：线程可以通过 await 之前必须调用 countDown 的次数）
    private static final CountDownLatch latch = new CountDownLatch(10);

    public static void main(String[] args) throws InterruptedException {

        int[] data = query();

        System.out.println("第一阶段。。。");

        // 并行处理数据
        for (int i = 0; i < data.length; i++) {
            executor.execute(new SimpleRunnable(data, i, latch));
        }

        // block住，等待计数器为0（等待第一阶段的所有线程和任务完成），或执行该语句（该语句指：latch.await()）的线程被中断
        latch.await();

        System.out.println("第二阶段。。。");

        // 异步操作，停止接收任务，线程池中的任务继续有序执行
        executor.shutdown();

    }

    private static int[] query() {
        return new int[]{1,2,3,4,5,6,7,8,9,10};
    }

    static class SimpleRunnable implements Runnable {

        private final int[] data;
        private final int index;
        private final CountDownLatch latch;

        SimpleRunnable(int[] data, int i, CountDownLatch latch) {
            this.data = data;
            this.index = i;
            this.latch = latch;
        }

        @Override
        public void run() {
            if (index % 2 == 0) {
                data[index] *= 2;
            } else {
                data[index] *= 3;
            }
            System.out.println(Thread.currentThread().getName() + " -> " + data[index]);
            // 计数减一
            latch.countDown();
        }

    }


}
