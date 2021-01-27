package com.code.thread.ee.lock.utils.oo.semaphore;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * API：
 * <br />{@link Semaphore#acquire(int) acquire(int)} —— 从信号量获取给定数量的许可证，会阻塞直至许可证获取成功
 * <br />{@link Semaphore#release(int) release(int)} —— 释放给定数量的许可证
 *
 * @author 愆凡
 */
@Slf4j
public class SemaphoreTest {

	private final Semaphore semaphore = new Semaphore(2);

	// 标识是否有已生产的数据
	private volatile boolean isHave = false;
	// 用来存储生产的数据
	private int num = 0;

	/**
	 * 一个生产者、消费者示例
	 */
	@Test
	public void consumerTest() throws InterruptedException {
		new Thread(this::provider, "T1").start();
		new Thread(this::consumer, "T2").start();

		Thread.currentThread().join();
	}

	private void provider() {
		while (true) {
			try {
				semaphore.acquire(2); // 从信号量获取许可证，获取不到阻塞在这里
				if (isHave) {
					semaphore.release(2);  // 释放许可证
					TimeUnit.SECONDS.sleep(1);
				} else {
					TimeUnit.SECONDS.sleep(1);
					System.err.println(Thread.currentThread().getName() + "，生产了 ：" + ++num);

					isHave = true;
					semaphore.release(2); // 释放许可证
				}
			} catch (InterruptedException e) {
				log.error("生产者发生异常 : ", e);
			}
		}
	}

	private void consumer() {
		while (true) {
			try {
				semaphore.acquire(2);
				if (!isHave) {
					semaphore.release(2);
					TimeUnit.SECONDS.sleep(1);
				} else {
					TimeUnit.SECONDS.sleep(1);
					System.err.println(Thread.currentThread().getName() + "，消费了 ：" + num);

					isHave = false;
					semaphore.release(2);
				}
			} catch (InterruptedException e) {
				log.error("消费者发生异常 : ", e);
			}
		}
	}

}
