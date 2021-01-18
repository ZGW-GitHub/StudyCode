package com.code.collection.concurrent.collection;

import org.junit.Test;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author 愆凡
 */
public class ConcurrentLinkedQueueTest {

	private final ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<>();

	@Test
	public void test() {
		queue.add("aaa");
		queue.add("bbb");
		queue.add("ccc");

		queue.forEach(System.err::println);
	}

}
