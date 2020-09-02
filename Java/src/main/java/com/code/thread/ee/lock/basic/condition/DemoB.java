package com.code.thread.ee.lock.basic.condition;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 只是用公平锁实现 生产者消费者 不行线程执行可能会混乱
 *
 * @author 愆凡
 */
public class DemoB {

	private static final ReentrantLock REENTRANT_LOCK = new ReentrantLock(true);

	private static final Condition CONDITION = REENTRANT_LOCK.newCondition();

	private static int test = 0;

	public static void main(String[] args) {
		new Thread(() -> {
			while (true) {
				DemoB.consume();
			}
		}).start();

		new Thread(() -> {
			while (true) {
				DemoB.product();
			}
		}).start();
	}

	// 生产者
	private static void product() {
		REENTRANT_LOCK.lock();
		try {
			TimeUnit.SECONDS.sleep(1);
			test++;
			System.out.println(Thread.currentThread().getName() + " -> 我生产了：" + test);
			CONDITION.signal();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			REENTRANT_LOCK.unlock();
		}
	}

	// 消费者
	private static void consume() {
		REENTRANT_LOCK.lock();
		try {
			TimeUnit.SECONDS.sleep(1);
			System.out.println(Thread.currentThread().getName() + " -> 我消费了：" + test);
			CONDITION.signal();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			REENTRANT_LOCK.unlock();
		}
	}


}
