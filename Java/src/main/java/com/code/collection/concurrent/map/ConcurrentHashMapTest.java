package com.code.collection.concurrent.map;

import org.junit.After;
import org.junit.Test;

import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.IntStream;

/**
 * @author 愆凡
 */
public class ConcurrentHashMapTest {

	private final ConcurrentHashMap<Integer, Integer> hashMap = new ConcurrentHashMap<>(0);
	
	@Test
	public void test() {
		IntStream.range(1, 64).forEach(i -> {
			hashMap.put(i, i);
			System.err.println("put i : " + i);
		});
	}
	
	@After
	public void after() {
		hashMap.forEach((k, v) -> System.err.println(k + " --- " + v));
	}

}
