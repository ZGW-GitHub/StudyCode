package com.code.thread.ee.lock.utils.aa.countdownlatch;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * API：
 * <br />{@link CountDownLatch#await() await()} —— 等待 countdown 达标
 * <br />{@link CountDownLatch#countDown() countDown()} —— countdown
 *
 * @author 愆凡
 * @date 2021/1/5 16:15
 */
@Slf4j
@SuppressWarnings("all")
public class CountDownLatchTest {

	/**
	 * 实现阶段任务
	 */
	@Test
	public void baseTest() throws InterruptedException {
		final CountDownLatch latch = new CountDownLatch(2);

		startNewThread(latch, 1, "T1");
		startNewThread(latch, 3, "T2");

		System.out.println("main await");
		latch.await();

		System.out.println("over ");
		
		Thread.currentThread().join();
	}

	/**
	 * 多线程 await
	 */
	@Test
	public void otherAwaitTest() throws InterruptedException {
		final CountDownLatch latch = new CountDownLatch(2);

		startNewThread(latch, 1, "T1");
		startNewThread(latch, 3, "T2");

		Thread t = new Thread(() -> {
			try {
				System.out.println(" t 等待中... ");
				System.out.println("Befor await() : count = " + latch.getCount());
				latch.await();
				System.out.println("After await() : count = " + latch.getCount());
				System.out.println(" t 等到了 ");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		t.start();

		System.out.println(" main 等待中... ");
		latch.await();
		System.out.println(" main 等到了 ");

		t.join();
	}

	private void startNewThread(CountDownLatch latch, int sleepSeconds, String threadName) {
		new Thread(() -> {
			try {
				TimeUnit.SECONDS.sleep(sleepSeconds);
			} catch (InterruptedException e) {
				log.error("Error : ", e);
			} finally {
				System.err.println(Thread.currentThread().getName() + " 阶段 over ");

				System.err.println("Befor countDown() : count = " + latch.getCount());
				latch.countDown();
				System.err.println("After countDown() : count = " + latch.getCount());
			}
		}, threadName).start();
	}

}
