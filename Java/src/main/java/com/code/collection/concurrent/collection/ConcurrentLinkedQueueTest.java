package com.code.collection.concurrent.collection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author 愆凡
 */
public class ConcurrentLinkedQueueTest {

	private final ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<>();

	@Before
	public void before() {
		queue.add("aaa");
		queue.add("bbb");
		queue.add("ccc");

		System.err.println(String.join("、", queue));
	}

	@After
	public void after() {
		System.err.println(String.join("、", queue));
	}

	@Test
	public void peekTest() {
		while (queue.peek() != null) {
			queue.remove();
		}
	}

}
