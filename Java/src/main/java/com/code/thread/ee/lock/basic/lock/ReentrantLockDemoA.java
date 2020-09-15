package com.code.thread.ee.lock.basic.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 愆凡
 * @date 2020/5/7 3:24 下午
 */
public class ReentrantLockDemoA {

	private static final Lock LOCK = new ReentrantLock();

	public static void main(String[] args) {

		new Thread(ReentrantLockDemoA::work, "Thread-A").start();
		new Thread(ReentrantLockDemoA::work, "Thread-B").start();

	}

	private static void work() {
		LOCK.lock();
		try {
			System.out.println(Thread.currentThread().getName() + " 抢到了锁！");
			Thread.sleep(3_000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			LOCK.unlock();
			System.out.println(Thread.currentThread().getName() + " 释放了锁！");
		}
	}

}