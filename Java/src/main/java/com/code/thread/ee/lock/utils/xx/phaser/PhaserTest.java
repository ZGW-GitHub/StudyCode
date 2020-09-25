package com.code.thread.ee.lock.utils.xx.phaser;

import java.util.concurrent.Phaser;

/**
 * 带参构造函数
 * 参数：
 * int ：
 * 使 phaser 包含给定数量的已注册未到达的参与方
 * 同时也规定了进入下一阶段所需的已到达参与方数量
 *
 * @author 愆凡
 */
public class PhaserTest {

	private static final Phaser phaser = new Phaser(1);

	public static void main(String[] args) {

		/*
		 * 获取当前相位（从0开始，最大为 Integer.MAX_VALUE ，超出从0重新开始，Phaser终止后其为-1）
		 */
		System.out.println(phaser.getPhase()); // 0
		phaser.arriveAndAwaitAdvance();
		System.out.println(phaser.getPhase()); // 1
		phaser.arriveAndAwaitAdvance();
		System.out.println(phaser.getPhase()); // 2
		phaser.arriveAndAwaitAdvance();
		System.out.println(phaser.getPhase()); // 3

		System.out.println("--------------------------");

		// 获取已注册的参与方数量
		System.out.println(phaser.getRegisteredParties()); // 起始为构造函数的int参数
		phaser.register();
		System.out.println(phaser.getRegisteredParties()); // +1
		phaser.register();
		System.out.println(phaser.getRegisteredParties()); // +1
		phaser.register();
		System.out.println(phaser.getRegisteredParties()); // +1

		System.out.println("--------------------------");

		// 获取抵达的参与方
		System.out.println(phaser.getArrivedParties());
		// 获取未抵达的参与方
		System.out.println(phaser.getUnarrivedParties());

		System.out.println("--------------------------");

		// 一次注册多个参与方（可以看成信号量）
		System.out.println(phaser.bulkRegister(10));
		System.out.println(phaser.getUnarrivedParties());

		System.out.println("--------------------------");

	}

}

