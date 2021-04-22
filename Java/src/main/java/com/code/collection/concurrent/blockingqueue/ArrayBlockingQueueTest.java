package com.code.collection.concurrent.blockingqueue;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * 有界
 *
 * @author 愆凡
 */
public class ArrayBlockingQueueTest {

	private final ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(2);

	// 将队列中的值复制到一个容器
	@Test
	public void drainToTest() {
		queue.add(1);
		queue.add(2);

		List<Integer> list = new ArrayList<>();

		queue.drainTo(list);

		System.out.println(list.toString());
	}

	// 剩余容量
	@Test
	public void testRemainingCapacity() {
		System.out.println(queue.remainingCapacity()); // 2

		queue.add(1);
		System.out.println(queue.remainingCapacity()); // 1

		queue.add(2);
		System.out.println(queue.remainingCapacity()); // 0
	}

}
