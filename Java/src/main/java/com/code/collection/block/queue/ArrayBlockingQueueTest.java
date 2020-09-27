package com.code.collection.block.queue;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 有界
 *
 * @author 愆凡
 */
public class ArrayBlockingQueueTest {

	private final ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(2);

	// 满，抛异常
	@Test
	public void testAdd() {
		queue.add(1);
		queue.add(2);

        System.out.println(queue.add(3)); // 抛异常
	}

	// 满，返回 false
	@Test
	public void testOffer() {
		queue.offer(1);
		queue.offer(2);

		System.out.println(queue.offer(3)); // 返回 false
	}

	// 满，阻塞
	@Test
	public void testPut() throws InterruptedException {
		queue.put(1);
		queue.put(2);

		new Thread(() -> queue.remove(1)).start();
		TimeUnit.SECONDS.sleep(1);

		queue.put(3); // 阻塞住

		new Thread(() -> queue.remove(2)).start();
		TimeUnit.SECONDS.sleep(1);

		queue.put(4); // 排后面

		System.out.println("结束！");
	}

	// 删除并返回头。
	// 空：返回null
	@Test
	public void testPoll() {
		queue.add(1);
		queue.add(2);

		System.out.println(queue.poll()); // 1
		System.out.println(queue.poll()); // 2
		System.out.println(queue.poll()); // null
	}

	// 返回头
	// 空：返回null
	@Test
	public void testPeek() {
		queue.add(1);
		queue.add(2);

		System.out.println(queue.peek()); // 1
		System.out.println(queue.peek()); // 1

		queue.clear();

		System.out.println(queue.peek()); // null
	}

	// 返回头
	// 空：抛异常
	@Test
	public void testElement() {
		queue.add(1);

		System.out.println(queue.element()); // 1
		System.out.println(queue.element()); // 1

		queue.clear();

		System.out.println(queue.element()); // 抛异常
	}

	// 删除并返回头。
	// 空：抛异常
	@Test
	public void testRemove() {

		queue.add(1);
		System.out.println(queue.remove()); // 1
		System.out.println(queue.remove()); // 抛异常

	}

	// 删除并返回头
	// 空：阻塞
	@Test
	public void testTake() throws InterruptedException {
		queue.add(1);
		System.out.println(queue.take());
		System.out.println(queue.take()); // 阻塞住
	}

	// 将队列中的值复制到一个容器
	@Test
	public void testDrainTo() {

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
