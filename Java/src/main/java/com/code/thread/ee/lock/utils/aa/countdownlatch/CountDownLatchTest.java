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
public class CountDownLatchTest {

	/**
	 * 通过 CountDownLatch 实现线程间通信
	 */
	@Test
	public void observerTest() {
		final CountDownLatch latch = new CountDownLatch(1);

		new Thread(() -> {
			System.err.println("为任务准备资源。。。");
			try {
				// 准备资源
				Thread.sleep(2_000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				// 资源准备完成（计数减一）
				latch.countDown();
			}
			System.err.println("准备资源完成。");
		}).start();

		new Thread(() -> {
			System.err.println("准备初始化任务。。。");
			try {
				// 初始化任务
				Thread.sleep(1_000);
				// 等待其它线程为其准备资源
				latch.await(); // block 住，等待计数器计数为0
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.err.println("任务开始执行。。。");
		}).start();
	}

	/**
	 * 测试 CountDownLatch 的等待超时
	 */
	@Test
	public void timeOutTest() throws InterruptedException {
		final CountDownLatch latch = new CountDownLatch(1);

		new Thread(() -> {
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				latch.countDown();
			}

		}).start();

		System.err.println("开始 wait");
		boolean isSuccess = latch.await(3, TimeUnit.SECONDS);
		System.err.println("结束！isSuccess : " + isSuccess);
	}

}
