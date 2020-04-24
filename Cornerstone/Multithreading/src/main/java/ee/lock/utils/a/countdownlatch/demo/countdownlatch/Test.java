package ee.lock.utils.a.countdownlatch.demo.countdownlatch;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.stream.IntStream;

public class Test {

    private static final Random random = new Random(System.currentTimeMillis());

    public static void main(String[] args) throws InterruptedException {

        final MyCountDownLatch myCountDownLatch = new MyCountDownLatch(5);
        final CountDownLatch countDownLatch = new CountDownLatch(5);

        System.out.println("准备开始任务。。。");
        System.out.println("第一阶段开始。。。");

        IntStream.rangeClosed(1,5).forEach((i)->
            new Thread(()->{
                System.out.println(Thread.currentThread().getName() + "工作了！");
                try {
                    Thread.sleep(random.nextInt(5000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "等待其它线程中！");
                countDownLatch.countDown();
//                myCountDownLatch.down();
            }, String.valueOf(i)).start()
        );

        countDownLatch.await();
//        myCountDownLatch.await();

        System.out.println("第二阶段开始。。。");

    }

}
