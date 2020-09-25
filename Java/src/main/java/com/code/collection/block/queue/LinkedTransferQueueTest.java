package com.code.collection.block.queue;

import java.util.concurrent.LinkedTransferQueue;

/**
 * 注意：
 * 无界的
 * <p>
 * 有担保地交付元素
 *
 * @author 愆凡
 */
public class LinkedTransferQueueTest {

	public static void main(String[] args) {

		LinkedTransferQueue<Object> queue = new LinkedTransferQueue<>();

        /*queue.add(2);
        System.out.println(queue.size());
        System.out.println(queue.size());

        System.out.println(queue.tryTransfer(3));*/

		queue.add(1);
		System.out.println(queue.remove());
		System.out.println(queue.poll());

	}

}