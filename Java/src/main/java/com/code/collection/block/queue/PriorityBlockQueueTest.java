/* ******************************
 *   Time:  2019-07-26 16:37    *
 ****************************** */
package com.code.collection.block.queue;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * 注意：
 * 大小无限（ 直到撑爆 JVM ）
 * <p>
 * 与 ArrayBlockingQueue 相比：
 * 增加了排序
 * put() 、offer()、add() 与 ArrayBlockingQueue 一致
 *
 * @author 愆凡
 */
public class PriorityBlockQueueTest {

	private static final PriorityBlockingQueue queue = new PriorityBlockingQueue();

	public static void main(String[] args) {


	}

}
