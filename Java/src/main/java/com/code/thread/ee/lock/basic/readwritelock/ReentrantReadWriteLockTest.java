package com.code.thread.ee.lock.basic.readwritelock;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author 愆凡
 * @date 2020/5/7 3:37 下午
 */
public class ReentrantReadWriteLockTest {

	private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
	private final Lock readLock = lock.readLock();
	private final Lock writeLock = lock.writeLock();

	private final CountDownLatch latch = new CountDownLatch(2);

	/**
	 * 读读 不互斥
	 */
	@Test
	public void readLockTest() throws InterruptedException {
		new Thread(() -> work(readLock, latch), "T1").start();
		new Thread(() -> work(readLock, latch), "T2").start();

		latch.await();
	}

	/**
	 * 写写 互斥
	 */
	@Test
	public void writeLockTest() throws InterruptedException {
		new Thread(() -> work(writeLock, latch), "T1").start();
		new Thread(() -> work(writeLock, latch), "T2").start();

		latch.await();
	}

	/**
	 * 读写 互斥
	 */
	@Test
	public void readWriteLockTest() throws InterruptedException {
		new Thread(() -> work(readLock, latch), "T1").start();
		new Thread(() -> work(writeLock, latch), "T2").start();

		latch.await();
	}

	private void work(Lock lock, CountDownLatch latch) {
		lock.lock();
		try {
			System.out.println(Thread.currentThread().getName() + " 抢到了锁！");
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
			System.out.println(Thread.currentThread().getName() + " 释放了锁！");
		}
		latch.countDown();
	}

}
