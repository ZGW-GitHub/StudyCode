package com.code.thread.ee.lock.basic.stampedlock;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.StampedLock;

/**
 * @author 愆凡
 * @date 2020/5/19 4:47 下午
 */
public class StampedLockTest {

	private final StampedLock stampedLock = new StampedLock();
	private final CountDownLatch latch = new CountDownLatch(2);

	/**
	 * 悲观读：读写测试
	 */
	@Test
	public void readTest() throws InterruptedException {
		new Thread(() -> doRead(latch), "T1").start();
		new Thread(() -> doWrite(latch), "T2").start();

		latch.await();
	}

	/**
	 * 乐观读：读写测试
	 */
	@Test
	public void optimisticReadTest() throws InterruptedException {
		new Thread(() -> doOptimisticRead(latch), "T1").start();
		new Thread(() -> doWrite(latch), "T2").start();

		latch.await();
	}

	/**
	 * 乐观读、悲观读：读读测试
	 */
	@Test
	public void optimisticReadTest2() throws InterruptedException {
		new Thread(() -> doOptimisticRead(latch), "T1").start();
		new Thread(() -> doRead(latch), "T2").start();

		latch.await();
	}

	private void doRead(CountDownLatch latch) {
		// 获取悲观读锁
		long readLock = stampedLock.readLock();

		try {
			System.out.println(Thread.currentThread().getName() + " 抢到了悲观读锁！");
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			// 释放悲观读锁
			stampedLock.unlockRead(readLock);
			System.out.println(Thread.currentThread().getName() + " 释放了悲观读锁！");

			latch.countDown();
		}
	}

	private void doOptimisticRead(CountDownLatch latch) {
		// 获取乐观读锁，乐观读锁不需要释放
		long optimisticRead = stampedLock.tryOptimisticRead();

		try {
			System.out.println(Thread.currentThread().getName() + " 抢到了乐观读锁！");
			TimeUnit.SECONDS.sleep(2);

			// 如果乐观读期间有写入，执行补偿机制，比如：获取悲观读来再次读
			if (!stampedLock.validate(optimisticRead)) {
				doRead(latch);
			} else {
				latch.countDown();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void doWrite(CountDownLatch latch) {
		// 获取写锁
		long writeLock = stampedLock.writeLock();

		try {
			System.out.println(Thread.currentThread().getName() + " 抢到了写锁！");
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			// 释放写锁
			stampedLock.unlockWrite(writeLock);
			System.out.println(Thread.currentThread().getName() + " 释放了写锁！");

			latch.countDown();
		}
	}

}
