package ee.lock.basic.cc.condition;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionTest {

    private static final ReentrantLock REENTRANT_LOCK = new ReentrantLock();

    private static final Condition CONDITION = REENTRANT_LOCK.newCondition();

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
			REENTRANT_LOCK.lock();
            while (have) {
				CONDITION.await();
            }
            TimeUnit.SECONDS.sleep(1);
            test++;
            System.out.println(Thread.currentThread().getName() + " -> 我生产了：" + test);
            have = true;
			CONDITION.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
			REENTRANT_LOCK.unlock();
        }

    }

    private static void consume() {

        try {
			REENTRANT_LOCK.lock();
            while (!have) {
				CONDITION.await();
            }
            TimeUnit.SECONDS.sleep(1);
            System.out.println(Thread.currentThread().getName() + " -> 我消费了：" + test);
            have = false;
			CONDITION.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
			REENTRANT_LOCK.unlock();
        }

    }


}
