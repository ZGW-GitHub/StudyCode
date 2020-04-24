package ee.lock.utils.aa.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

// 中断结束
// 超时结束
public class CountDownLatchTestD {

    public static void main(String[] args) throws InterruptedException {

        final CountDownLatch latch = new CountDownLatch(1);

        final Thread mainThread = Thread.currentThread();

        new Thread(()->{
            System.out.println("开始 wait");
            try {
                Thread.sleep(30_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // 执行中断
//            mainThread.interrupt();

        }).start();

        latch.await(1000, TimeUnit.MILLISECONDS);

        System.out.println("结束！");

    }

}
