package com.code.thread.ee.lock.utils.cc.cyclicbarrier;

import org.junit.Test;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * @author 愆凡
 */
public class CyclicBarrierTest {

	@Test
	public void test() throws BrokenBarrierException, InterruptedException {

		final CyclicBarrier barrier = new CyclicBarrier(3,
				() -> System.err.println(Thread.currentThread().getName() + " 回调函数！"));

		System.err.println("main start !");

		new Thread(() -> {
			try {
				System.err.println("Thread 1 start !");
				TimeUnit.MILLISECONDS.sleep(2_000);
				barrier.await();
				System.err.println("Thread 1 over !");
			} catch (InterruptedException | BrokenBarrierException e) {
				e.printStackTrace();
			}
		}).start();

		new Thread(() -> {
			try {
				System.err.println("Thread 2 start !");
				TimeUnit.MILLISECONDS.sleep(3_000);
				barrier.await();
				System.err.println("Thread 2 over !");
			} catch (InterruptedException | BrokenBarrierException e) {
				e.printStackTrace();
			}
		}).start();

		barrier.await();

		System.err.println("main over !");
	}

}
