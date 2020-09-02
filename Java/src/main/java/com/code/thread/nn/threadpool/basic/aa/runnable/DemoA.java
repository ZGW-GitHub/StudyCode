package com.code.thread.nn.threadpool.basic.aa.runnable;

/**
 * @author 愆凡
 * @date 2020/5/11 9:14 上午
 */
public class DemoA {
	public static void main(String[] args) throws InterruptedException {

		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				System.out.println("This is Runnable !");
			}
		};

		Thread thread = new Thread(runnable);
		thread.start();

		thread.join();

	}
}
