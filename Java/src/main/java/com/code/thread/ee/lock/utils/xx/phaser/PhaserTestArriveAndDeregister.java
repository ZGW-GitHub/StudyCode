package com.code.thread.ee.lock.utils.xx.phaser;

import java.util.Random;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

// 注销 arriveAndDeregister()
// 提前到达了并等待 arriveAndAwaitAdvance()
public class PhaserTestArriveAndDeregister {

	private static final Random random = new Random(System.currentTimeMillis());

	private static final Phaser phaser = new Phaser();

	public static void main(String[] args) throws InterruptedException {

		IntStream.rangeClosed(1, 5).boxed().map(i -> phaser).forEach(ThreadDemo::new);

		new ThreadDemo2(phaser);

		TimeUnit.SECONDS.sleep(10);

	}

	private static class ThreadDemo extends Thread {

		private final Phaser phaser;

		private ThreadDemo(Phaser phaser) {
			this.phaser = phaser;
			// 注册
			this.phaser.register();
			// Thread 的方法，作用：执行 run 方法
			start();
		}

		@Override
		public void run() {
			try {

				System.out.println("第一轮：");
				System.out.println(Thread.currentThread().getName() + " ---> 我来了。");
				TimeUnit.SECONDS.sleep(random.nextInt(2));
				// arrive：到达
				// advance：提前
				// 提前到达了并等待
				phaser.arriveAndAwaitAdvance();

				System.out.println("第二轮：");
				System.out.println(Thread.currentThread().getName() + " ---> 我来了。");
				TimeUnit.SECONDS.sleep(random.nextInt(2));
				// arrive：到达
				// advance：提前
				// 提前到达了并等待
				phaser.arriveAndAwaitAdvance();

				System.out.println("第三轮：");
				System.out.println(Thread.currentThread().getName() + " ---> 我来了。");
				TimeUnit.SECONDS.sleep(random.nextInt(2));
				// arrive：到达
				// advance：提前
				// 提前到达了并等待
				phaser.arriveAndAwaitAdvance();

			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}

	private static class ThreadDemo2 extends Thread {

		private final Phaser phaser;

		private ThreadDemo2(Phaser phaser) {
			super("退赛的选手");
			this.phaser = phaser;
			// 注册
			this.phaser.register();
			// Thread 的方法，作用：执行 run 方法
			start();
		}

		@Override
		public void run() {
			try {

				System.out.println("第一轮：");
				System.out.println(Thread.currentThread().getName() + " ---> 我来了。");
				TimeUnit.SECONDS.sleep(random.nextInt(2));


				// arrive：到达
				// advance：提前
				// 提前到达了并等待
				phaser.arriveAndAwaitAdvance();

				System.out.println("第二轮：");
				System.out.println(Thread.currentThread().getName() + " ---> 我受伤了，我退出。");
				TimeUnit.SECONDS.sleep(random.nextInt(2));
				// arrive：到达
				// Deregister：注销
				phaser.arriveAndDeregister();

				System.out.println("第三轮：");
				System.out.println(Thread.currentThread().getName() + " ---> 退赛的选手。");
				TimeUnit.SECONDS.sleep(random.nextInt(2));
				// 注意：不用再注销了

			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}

}
