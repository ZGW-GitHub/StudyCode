package learn3_LockUtils.v1_CountDownLatch;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CountDownLatch_test2 {

    // 线程池（参数：线程可以通过 await 之前必须调用 countDown 的次数）
    private static ExecutorService executor = Executors.newFixedThreadPool(2);

    public static void main(String[] args) throws InterruptedException {

        int[] data = query();

        System.out.println("第一阶段。。。");

        // 并行处理数据
        for (int i = 0; i < data.length; i++) {
            executor.execute(new SimpleRunnable(data, i));
        }

        // 异步操作，停止接收任务，线程池中的任务继续有序执行
        executor.shutdown();
        // block住，阻止所有线程的任务在shutdown后执行 并在 超时后 再继续执行程序
        executor.awaitTermination(1, TimeUnit.HOURS);

        System.out.println("第二阶段。。。");

    }

    private static int[] query() {
        return new int[]{1,2,3,4,5,6,7,8,9,10};
    }

    static class SimpleRunnable implements Runnable {

        private final int[] data;
        private final int index;

        SimpleRunnable(int[] data, int i) {
            this.data = data;
            this.index = i;
        }

        @Override
        public void run() {
            if (index % 2 == 0) {
                data[index] *= 2;
            } else {
                data[index] *= 3;
            }
            System.out.println(Thread.currentThread().getName() + " -> " + data[index]);

        }

    }


}
