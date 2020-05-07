package ee.lock.basic.aa.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author 愆凡
 * @date 2020/5/7 3:37 下午
 */
public class ReentrantReadLockDemoA {

	private static final ReentrantReadWriteLock REENTRANT_READ_WRITE_LOCK = new ReentrantReadWriteLock();
	private static final Lock READ_LOCK = REENTRANT_READ_WRITE_LOCK.readLock();

	public static void main(String[] args) {

		new Thread(ReentrantReadLockDemoA::work, "Thread-A").start();
		new Thread(ReentrantReadLockDemoA::work, "Thread-B").start();

	}

	private static void work() {
		READ_LOCK.lock();
		try {
			System.out.println(Thread.currentThread().getName() + " 抢到了锁！");
			Thread.sleep(3_000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			READ_LOCK.unlock();
			System.out.println(Thread.currentThread().getName() + " 释放了锁！");
		}
	}

}
