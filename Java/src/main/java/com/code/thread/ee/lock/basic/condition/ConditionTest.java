package com.code.thread.ee.lock.basic.condition;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * API：
 * <br />{@link Condition#await() await()} —— 等待 signal 唤醒
 * <br />{@link Condition#signal() signal()} —— 唤醒等待线程
 *
 * @author 愆凡
 * @date 2020/5/7 2:06 下午
 */
@Slf4j
public class ConditionTest {

	// 初始化一个非公平的可重入锁
	private final ReentrantLock lock = new ReentrantLock();
	// 初始化一个 Condition 对象
	private final Condition condition = lock.newCondition();

	// 生产者消费者示例，标识是否有已生产的数据
	private volatile boolean isHave = false;
	// 生产者消费者示例，用来存储生产的数据
	private int num = 0;

	/**
	 * 一个生产者、消费者示例
	 */
	@Test
	public void test() throws InterruptedException {
		new Thread(this::provider, "T1").start();
		new Thread(this::consumer, "T2").start();

		Thread.currentThread().join();
	}

	private void provider() {
		while (true) {
			lock.lock(); // 加锁
			try {
				if (!isHave) {
					TimeUnit.SECONDS.sleep(1);
					System.err.println(Thread.currentThread().getName() + "，生产了 ：" + ++num);

					isHave = true;
					condition.signal(); // 唤醒消费者
				} else {
					condition.await(); // 等待生产者生产
				}
			} catch (InterruptedException e) {
				log.error("生产者发生异常 : ", e);
			} finally {
				lock.unlock(); // 释放锁
			}
		}
	}

	private void consumer() {
		while (true) {
			lock.lock();
			try {
				if (isHave) {
					TimeUnit.SECONDS.sleep(1);
					System.err.println(Thread.currentThread().getName() + "，消费了 ：" + num);

					isHave = false;
					condition.signal();
				} else {
					condition.await();
				}
			} catch (InterruptedException e) {
				log.error("消费者发生异常 : ", e);
			} finally {
				lock.unlock();
			}
		}
	}

}
