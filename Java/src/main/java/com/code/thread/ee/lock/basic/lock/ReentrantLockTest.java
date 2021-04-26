package com.code.thread.ee.lock.basic.lock;

import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 愆凡
 * @date 2020/5/7 3:24 下午
 */
@SuppressWarnings("all")
public class ReentrantLockTest {

	private final Lock lock = new ReentrantLock();

	@Test
	public void test() throws InterruptedException {
		new Thread(this::work, "T1").start();
		TimeUnit.MILLISECONDS.sleep(500);
		new Thread(this::work, "T2").start();
		TimeUnit.MILLISECONDS.sleep(500);
		new Thread(this::work, "T3").start();
		TimeUnit.MILLISECONDS.sleep(500);
		new Thread(this::work, "T4").start();
		TimeUnit.MILLISECONDS.sleep(500);
		new Thread(this::work, "T5").start();
		TimeUnit.MILLISECONDS.sleep(500);
		new Thread(this::work, "T6").start();

		Thread.currentThread().join();
	}

	private void work() {
		lock.lock();
		try {
			System.out.println(Thread.currentThread().getName() + " 抢到了锁！");
			TimeUnit.SECONDS.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
			System.out.println(Thread.currentThread().getName() + " 释放了锁！");
		}
	}

}
