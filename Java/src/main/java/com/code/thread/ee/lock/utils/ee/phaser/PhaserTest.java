package com.code.thread.ee.lock.utils.ee.phaser;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * API：
 * <br />{@link Phaser#getPhase() getPhase()} —— 获取当前阶段
 * <br />{@link Phaser#register() register()} —— 注册一个参与者
 * <br />{@link Phaser#arriveAndAwaitAdvance() arriveAndAwaitAdvance()} —— 等待其它参与者到达
 *
 * @author 愆凡
 */
@Slf4j
public class PhaserTest {

	@Test
	public void baseTest() {
		final Phaser phaser = new Phaser();

		startNewThread(phaser, 1, "T1");
		startNewThread(phaser, 2, "T2");
		startNewThread(phaser, 3, "T3");

		System.err.println(phaser.getPhase()); // 获取当前阶段

		phaser.register(); // 注册一个参与者
		phaser.arriveAndAwaitAdvance(); // 等待其它参与者到达

		System.err.println(phaser.getPhase()); // 获取当前阶段

		System.err.println("over !");
	}

	@Test
	public void baseTest2() {
		final Phaser phaser = new Phaser();

		startNewThread(phaser, 1, "T1");
		startNewThread(phaser, 2, "T2");
		startNewThread(phaser, 3, "T3");

		System.err.println(phaser.getPhase()); // 获取当前阶段

//		phaser.register(); // 注册一个参与者
		phaser.arriveAndAwaitAdvance(); // 等待其它参与者到达

		System.err.println(phaser.getPhase()); // 获取当前阶段

		System.err.println("over !");
	}

	private void startNewThread(Phaser phaser, int sleepSeconds, String threadName) {
		new Thread(() -> {
			try {
				phaser.register(); // 注册一个参与者

				TimeUnit.SECONDS.sleep(sleepSeconds);

				System.err.println(Thread.currentThread().getName() + " 阶段 over ");

				phaser.arriveAndAwaitAdvance(); // 等待其它参与者到达
			} catch (InterruptedException e) {
				log.error("Error : ", e);
			}
		}, threadName).start();
	}

}

