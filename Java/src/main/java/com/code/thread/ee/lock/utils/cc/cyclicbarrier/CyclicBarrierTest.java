package com.code.thread.ee.lock.utils.cc.cyclicbarrier;

import org.junit.Test;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * API：
 * <br />{@link CyclicBarrier#await()} await()} —— 相互等待
 * <br />{@link CyclicBarrier#reset()} reset()} ——
 *
 * @author 愆凡
 */
public class CyclicBarrierTest {

	@Test
	public void awaitTest() throws BrokenBarrierException, InterruptedException {

		final CyclicBarrier barrier = new CyclicBarrier(3,
				// 最后一个完成的线程将执行该回调函数
				() -> System.err.println(Thread.currentThread().getName() + " 执行回调函数！"));

		new Thread(() -> {
			try {
				TimeUnit.MILLISECONDS.sleep(3_000);
				System.err.println(Thread.currentThread().getName() + " awaiting ...");
				barrier.await(); // 相互等待
			} catch (InterruptedException | BrokenBarrierException e) {
				e.printStackTrace();
			}
		}, "T1").start();

		new Thread(() -> {
			try {
				TimeUnit.MILLISECONDS.sleep(2_000);
				System.err.println(Thread.currentThread().getName() + " awaiting ...");
				barrier.await(); // 相互等待
			} catch (InterruptedException | BrokenBarrierException e) {
				e.printStackTrace();
			}
		}, "T2").start();

		System.err.println(Thread.currentThread().getName() + " awaiting ...");
		barrier.await(); // 相互等待

		System.err.println("over !");
	}

}
