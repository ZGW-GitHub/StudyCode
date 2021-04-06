package com.code.thread.aa.thread.ee.close;

/**
 * 通过 中断标识位 来终止线程，有 BUG
 *
 * @author 愆凡
 */
public class DemoB {

	public static void main(String[] args) {
		Worker worker = new Worker();
		worker.start();

		try {
			Thread.sleep(1_000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		worker.interrupt();
	}

	private static class Worker extends Thread {

		@Override
		public void run() {
			while (true) {
				if (Thread.interrupted()) {
					break;
				}
				// 干活
			}
			// 善后：当被中断可以跳出循环，就能执行“善后”工作了 。
		}

	}

}
