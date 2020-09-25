package com.code.thread.ee.lock.basic.condition;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

/**
 * 只是用公平锁实现 生产者消费者 不行线程执行可能会混乱
 *
 * @author 愆凡
 */
public class DemoC {

	private static final ReentrantLock LOCK = new ReentrantLock(true);

	private static final Condition PRODUCT_CONDITION = LOCK.newCondition();

	private static final Condition CONSUMER_CONDITION = LOCK.newCondition();

	private static final LinkedList<Long> LIST_DATA = new LinkedList<>();

	private static final int MAX_LENGTH = 100;

	public static void main(String[] args) {

		IntStream.range(1, 5).forEach(DemoC::createThread);

	}

	private static void createThread(int num) {
		String i = String.valueOf(num);
		new Thread(() -> {
			while (true) {
				DemoC.consume();
				try {
					TimeUnit.SECONDS.sleep(3);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, i + "消费者").start();

		new Thread(() -> {
			while (true) {
				DemoC.product();
				try {
					TimeUnit.SECONDS.sleep(2);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, i + "生产者").start();

	}

	// 生产者
	private static void product() {

		LOCK.lock();
		try {
			while (LIST_DATA.size() > MAX_LENGTH) {
				PRODUCT_CONDITION.await();
			}
			long data = System.currentTimeMillis();
			LIST_DATA.addLast(data);
			System.out.println(Thread.currentThread().getName() + " -> 我生产了：" + data);
			CONSUMER_CONDITION.signal();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			LOCK.unlock();
		}

	}

	// 消费者
	private static void consume() {

		LOCK.lock();
		try {
			while (LIST_DATA.isEmpty()) {
				CONSUMER_CONDITION.await();
			}
			Long data = LIST_DATA.removeFirst();
			System.out.println(Thread.currentThread().getName() + " -> 我消费了：" + data);
			PRODUCT_CONDITION.signal();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			LOCK.unlock();
		}

	}


}
