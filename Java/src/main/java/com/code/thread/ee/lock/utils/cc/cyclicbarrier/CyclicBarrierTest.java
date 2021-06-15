package com.code.thread.ee.lock.utils.cc.cyclicbarrier;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * API：
 * <br />{@link CyclicBarrier#await() await()} —— 相互等待
 * <br />{@link CyclicBarrier#reset() reset()} ——
 *
 * @author 愆凡
 */
@Slf4j
@SuppressWarnings("all")
public class CyclicBarrierTest {

	/**
	 * 实现阶段任务
	 */
	@Test
	public void baseTest() throws BrokenBarrierException, InterruptedException {

		// 最后一个完成的线程将执行该回调函数
		final CyclicBarrier barrier = new CyclicBarrier(3, () -> {
			try {
				System.err.println(" 阶段完成，" + Thread.currentThread().getName() + " 执行回调函数！");
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				log.error("Error : " + e);
			}
		});

		startNewThread(barrier, 3, "T1");
		startNewThread(barrier, 2, "T2");

		System.err.println(" 等待阶段完成... ");
		barrier.await(); // 相互等待

		// 执行完方法回调才会继续执行到这里
		System.err.println("over !");
	}

	/**
	 * 实现多阶段任务
	 */
	@Test
	public void baseTest2() throws BrokenBarrierException, InterruptedException {
		final CyclicBarrier barrier = new CyclicBarrier(3, () -> System.err.println(Thread.currentThread().getName() + " 执行回调函数！"));

		startNewThread(barrier, 2, "T1");
		startNewThread(barrier, 3, "T2");
		startNewThread(barrier, 4, "T3");
		startNewThread(barrier, 5, "T4");

		System.err.println(" 等待阶段完成... ");
		barrier.await();
		System.err.println(" 阶段完成 ");

		System.err.println(" 等待阶段完成... ");
		barrier.await();
		System.err.println(" 阶段完成 ");

		System.err.println("over !");
	}

	@Test
	public void resetTest() throws BrokenBarrierException, InterruptedException {
		final CyclicBarrier barrier = new CyclicBarrier(3, () -> System.err.println(Thread.currentThread().getName() + " 执行回调函数！"));

		startNewThread(barrier, 1, "T1");
		startNewThread(barrier, 6, "T2");
		startNewThread(barrier, 8, "T3");

		TimeUnit.SECONDS.sleep(3);

		System.out.println("main-1 开始 await ");
		try {
			barrier.await(2, TimeUnit.SECONDS);
		} catch (TimeoutException e) {
			System.err.println("main-1 await 超时");
			barrier.reset();
		}
		System.out.println("main-1 结束 await ");

		System.out.println("main-2 开始 await ");
		barrier.await();
		System.out.println("main-2 结束 await ");

		Thread.currentThread().join();
	}

	private void startNewThread(CyclicBarrier barrier, int sleepSeconds, String threadName) {
		new Thread(() -> {
			try {
				TimeUnit.SECONDS.sleep(sleepSeconds);

				System.err.println(Thread.currentThread().getName() + " 开始 await ");

				barrier.await(); // 等待

				System.err.println(Thread.currentThread().getName() + " 结束 await ");
			} catch (InterruptedException | BrokenBarrierException e) {
//				log.error("Error : ", e);
				if (e instanceof BrokenBarrierException) {
					log.error("屏障坏了");
				}
			}
		}, threadName).start();
	}

}
