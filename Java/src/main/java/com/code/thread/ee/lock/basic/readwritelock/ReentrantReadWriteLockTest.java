package com.code.thread.ee.lock.basic.readwritelock;

import org.junit.Test;

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

	@Test
	public void readLockTest() throws InterruptedException {
		new Thread(() -> work(readLock), "T1").start();
		new Thread(() -> work(readLock), "T2").start();

		TimeUnit.SECONDS.sleep(3);
	}

	@Test
	public void writeLockTest() throws InterruptedException {
		new Thread(() -> work(writeLock), "T1").start();
		new Thread(() -> work(writeLock), "T2").start();

		Thread.currentThread().join();
	}

	private void work(Lock lock) {
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
	}

}
