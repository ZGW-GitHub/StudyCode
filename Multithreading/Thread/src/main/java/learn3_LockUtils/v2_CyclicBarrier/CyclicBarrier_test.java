package learn3_LockUtils.v2_CyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

public class CyclicBarrier_test {

    private static final CyclicBarrier barrier = new CyclicBarrier(3, () -> System.out.println("回调函数！"));

    public static void main(String[] args) throws InterruptedException, BrokenBarrierException {

        new Thread(()->{
            try {
                System.out.println("Thread 1 start !");
                TimeUnit.MILLISECONDS.sleep(2_000);
                barrier.await();
                System.out.println("Thread 1 over !");
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(()->{
            try {
                System.out.println("Thread 2 start !");
                TimeUnit.MILLISECONDS.sleep(8_000);
                barrier.await();
                System.out.println("Thread 2 over !");
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }).start();

        barrier.await();

        System.out.println("main over !");

    }

}
