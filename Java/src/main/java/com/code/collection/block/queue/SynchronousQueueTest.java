/* ******************************
 *    Date:  2019-07-30 9:57    *
 ****************************** */
package com.code.collection.block.queue;

import java.util.concurrent.SynchronousQueue;

/**
 * 注意：
 * 没有容量（没有就是没有，而不是容量为1）
 * when you want to insert must wait for remove .（反之亦然）
 * 不允许NUL
 *
 * @author 愆凡
 */
public class SynchronousQueueTest {

	public static void main(String[] args) {

		SynchronousQueue<Integer> synchronousQueue = new SynchronousQueue<>();

//        Executors.newCachedThreadPool(); // 这里用到了

	}

}