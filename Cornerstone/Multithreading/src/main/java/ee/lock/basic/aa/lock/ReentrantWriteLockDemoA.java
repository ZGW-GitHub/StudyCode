package ee.lock.basic.aa.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author 愆凡
 * @date 2020/5/7 3:42 下午
 */
public class ReentrantWriteLockDemoA {

	private static final ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
	private static final Lock WRITE_LOCK = reentrantReadWriteLock.writeLock();

	public static void main(String[] args) {

		new Thread(ReentrantWriteLockDemoA::work, "Thread-A").start();
		new Thread(ReentrantWriteLockDemoA::work, "Thread-B").start();

	}

	private static void work() {
		WRITE_LOCK.lock();
		try {
			System.out.println(Thread.currentThread().getName() + " 抢到了锁！");
			Thread.sleep(3_000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			WRITE_LOCK.unlock();
			System.out.println(Thread.currentThread().getName() + " 释放了锁！");
		}
	}

}
