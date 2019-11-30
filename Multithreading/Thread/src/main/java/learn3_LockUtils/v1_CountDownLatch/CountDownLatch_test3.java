package learn3_LockUtils.v1_CountDownLatch;

import java.util.concurrent.CountDownLatch;

// 协同作业
public class CountDownLatch_test3 {

    // 线程池（参数：线程可以通过 await 之前必须调用 countDown 的次数）
    private static final CountDownLatch latch = new CountDownLatch(1);

    public static void main(String[] args) {

        new Thread(() -> {
            System.out.println("准备初始化任务。。。");
            try {
                // 初始化任务
                Thread.sleep(1_000);
                // 等待其它线程为其准备资源
                latch.await(); // block 住，等待计数器计数为0
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("任务开始执行。。。");
        }).start();

        new Thread(() -> {
            System.out.println("为任务准备资源。。。");
            try {
                // 准备资源
                Thread.sleep(2_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                // 资源准备完成（计数减一）
                latch.countDown();
            }
            System.out.println("准备资源完成。");
        }).start();

    }

}
