package com.code.thread.ee.lock.utils.cc.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * @author 愆凡
 */
public class CyclicBarrierTest {

	private static final CyclicBarrier barrier = new CyclicBarrier(3, () -> System.out.println(Thread.currentThread().getName() + " 回调函数！"));

	public static void main(String[] args) throws InterruptedException, BrokenBarrierException {

		System.out.println("main start !");

		new Thread(() -> {
			try {
				System.out.println("Thread 1 start !");
				TimeUnit.MILLISECONDS.sleep(2_000);
				barrier.await();
				System.out.println("Thread 1 over !");
			} catch (InterruptedException | BrokenBarrierException e) {
				e.printStackTrace();
			}
		}).start();

		new Thread(() -> {
			try {
				System.out.println("Thread 2 start !");
				TimeUnit.MILLISECONDS.sleep(8_000);
				barrier.await();
				System.out.println("Thread 2 over !");
			} catch (InterruptedException | BrokenBarrierException e) {
				e.printStackTrace();
			}
		}).start();

		barrier.await();

		System.out.println("main over !");

	}

}
