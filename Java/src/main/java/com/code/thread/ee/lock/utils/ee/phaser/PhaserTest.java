package com.code.thread.ee.lock.utils.ee.phaser;

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

	private final Phaser phaser = new Phaser(1);


}

