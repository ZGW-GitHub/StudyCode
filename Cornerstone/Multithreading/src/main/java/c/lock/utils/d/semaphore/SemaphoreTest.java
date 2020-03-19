package c.lock.utils.d.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

// 使用单个信号量自定义定义锁
public class SemaphoreTest {

    private static final DemoSemaphoreLock lockDemo = new DemoSemaphoreLock();

    public static void main(String[] args) {

        new Thread(()->{
            try {
                lockDemo.lock();
                System.out.println(Thread.currentThread().getName() + " : I get the lock !");
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lockDemo.unlock();
                System.out.println(Thread.currentThread().getName() + " : the lock is free !");
            }
        }, "==Aa==").start();

        new Thread(()->{
            try {
                lockDemo.lock();
                System.out.println(Thread.currentThread().getName() + " : I get the lock !");
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lockDemo.unlock();
                System.out.println(Thread.currentThread().getName() + " : the lock is free !");
            }
        }, "==B==").start();

    }

    /**
     * 自定义锁
     */
    static class DemoSemaphoreLock {

        private final Semaphore semaphore = new Semaphore(1);

        void lock() throws InterruptedException {
            semaphore.acquire();
        }

        void unlock() {
            semaphore.release();
        }

    }

}
