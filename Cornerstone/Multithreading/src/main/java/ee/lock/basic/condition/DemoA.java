package ee.lock.basic.condition;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 愆凡
 * @date 2020/5/7 2:06 下午
 */
public class DemoA {

	private static final ReentrantLock REENTRANT_LOCK = new ReentrantLock();

	private static final Condition CONDITION = REENTRANT_LOCK.newCondition();

	private static volatile boolean have = false;

	private static int test = 0;

	public static void main(String[] args) {

		new Thread(()->{
			while (true) {
				DemoA.consumer();
			}
		}).start();

		new Thread(()->{
			while (true) {
				DemoA.provider();
			}
		}).start();

	}

	private static void provider() {
		REENTRANT_LOCK.lock();
		try {
			while (have) {
				CONDITION.await();
			}
			TimeUnit.SECONDS.sleep(2);
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

	private static void consumer() {
		REENTRANT_LOCK.lock();
		try {
			while (!have) {
				CONDITION.await();
			}
			TimeUnit.SECONDS.sleep(2);
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
