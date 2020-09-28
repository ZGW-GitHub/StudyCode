package com.code.collection.block.queue;

import org.junit.Test;

import java.util.concurrent.SynchronousQueue;

/**
 * 有界
 *
 * @author 愆凡
 */
public class SynchronousQueueTest {

	private final SynchronousQueue<Integer> queue = new SynchronousQueue<>();

	@Test
	public void putTest() throws InterruptedException {
		queue.put(0);
		queue.put(1);
	}

	@Test
	public void takeTest() throws InterruptedException {
		System.out.println(queue.take());
	}

}
