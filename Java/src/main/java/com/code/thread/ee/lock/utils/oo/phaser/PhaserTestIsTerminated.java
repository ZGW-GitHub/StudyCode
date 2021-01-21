package com.code.thread.ee.lock.utils.oo.phaser;

import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/*
 * forceTermination()
 *      强制此移相器进入终止状态。注册方的数量不受影响。
 *      如果此移相器是分层相位器的成员，则该组中的所有移相器都将终止。
 *      如果此移相器已终止，则此方法无效。
 *      在一个或多个任务遇到意外异常后，此方法可用于协调恢复。
 *
 * @author 愆凡
 */
public class PhaserTestIsTerminated {

	public static void main(String[] args) throws InterruptedException {

		final Phaser phaser = new Phaser(3);

		new Thread(phaser::arriveAndAwaitAdvance).start();

		TimeUnit.SECONDS.sleep(2);

		System.out.println(phaser.isTerminated()); // false

		// 强制该相量进入终止状态。
		phaser.forceTermination();

		System.out.println(phaser.isTerminated()); // true

	}

}
