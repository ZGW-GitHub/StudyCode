package com.code.thread.ee.lock.utils.oo.phaser;

import java.util.Random;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

// 动态增加 register() ：增加一个未到达的参与者
public class PhaserTestRegister {

	private static final Random random = new Random(System.currentTimeMillis());

	private static final Phaser phaser = new Phaser();

	public static void main(String[] args) throws InterruptedException {

		IntStream.rangeClosed(1, 4).boxed().map(i -> phaser).forEach(ThreadDemo::new);

		// 注册（未到达的参与者加一）
		phaser.register();

		for (int i = 0; i < 5; i++) {
			TimeUnit.SECONDS.sleep(1);
			System.out.println(phaser.getArrivedParties());
		}

		phaser.arriveAndAwaitAdvance();

		System.out.println(Thread.currentThread().getName() + " ---> 都结束了。");

		new Thread(() -> {
			phaser.register();
			try {
				System.out.println("注册了！");
				System.out.println(phaser.getUnarrivedParties() + " 4OldThread的register() + main的register() + thisThread的register() = 6");
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			phaser.arriveAndAwaitAdvance();
		}).start();

	}

	private static class ThreadDemo extends Thread {

		private final Phaser phaser;

		private ThreadDemo(Phaser phaser) {
			this.phaser = phaser;
			// 注册（未到达的参与者加一）
			this.phaser.register();
			// Thread 的方法，作用：执行 run 方法
			start();
		}

		@Override
		public void run() {
			try {
				System.out.println("第一轮：");
				System.out.println(Thread.currentThread().getName() + " ---> 我来了。");
				TimeUnit.SECONDS.sleep(random.nextInt(5));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			// arrive：到达
			// advance：提前
			// 提前到达了并等待
			phaser.arriveAndAwaitAdvance();

		}
	}

}
