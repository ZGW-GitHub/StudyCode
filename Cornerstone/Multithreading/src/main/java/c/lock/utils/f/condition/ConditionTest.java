package c.lock.utils.f.condition;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

// 等待/通知
//      有 《 类似 wait 和 notify 》 的 API
public class ConditionTest {

    private static final ReentrantLock lock = new ReentrantLock();

    private static final Condition condition = lock.newCondition();

    private static boolean have = false;

    private static int test = 0;

    public static void main(String[] args) {

        new Thread(()->{
            while (true) {
                ConditionTest.consume();
            }
        }).start();

        new Thread(()->{
            while (true) {
                ConditionTest.product();
            }
        }).start();

    }

    private static void product() {

        try {
            lock.lock();
            while (have) {
                condition.await();
            }
            TimeUnit.SECONDS.sleep(1);
            test++;
            System.out.println(Thread.currentThread().getName() + " -> 我生产了：" + test);
            have = true;
            condition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    private static void consume() {

        try {
            lock.lock();
            while (!have) {
                condition.await();
            }
            TimeUnit.SECONDS.sleep(1);
            System.out.println(Thread.currentThread().getName() + " -> 我消费了：" + test);
            have = false;
            condition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }


}
