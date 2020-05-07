package ee.lock.basic.cc.condition;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

// 只是用公平锁实现 生产者消费者 不行线程执行可能会混乱
public class ConditionTest2 {

    private static final ReentrantLock lock = new ReentrantLock(true);

    private static final Condition condition = lock.newCondition();

    private static boolean have = false;

    private static int test = 0;

    public static void main(String[] args) {

        new Thread(()->{
            while (true)
                ConditionTest2.consume();
        }).start();

        new Thread(()->{
            while (true)
                ConditionTest2.product();
        }).start();

    }

    // 生产者
    private static void product() {

        try {
            lock.lock();
            TimeUnit.SECONDS.sleep(1);
            test++;
            System.out.println(Thread.currentThread().getName() + " -> 我生产了：" + test);
            condition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    // 消费者
    private static void consume() {

        try {
            lock.lock();
            TimeUnit.SECONDS.sleep(1);
            System.out.println(Thread.currentThread().getName() + " -> 我消费了：" + test);
            condition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }


}
