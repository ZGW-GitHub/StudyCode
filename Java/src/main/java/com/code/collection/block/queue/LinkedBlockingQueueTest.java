package com.code.collection.block.queue;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * 有界
 *
 * @author 愆凡
 */
public class LinkedBlockingQueueTest {

	public static void main(String[] args) throws InterruptedException {

		LinkedBlockingQueue<Object> queue = new LinkedBlockingQueue<>();

		queue.put(null);

	}

}
