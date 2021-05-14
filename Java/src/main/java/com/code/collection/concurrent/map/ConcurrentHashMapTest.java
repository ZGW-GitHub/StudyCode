package com.code.collection.concurrent.map;

import org.junit.After;
import org.junit.Test;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 愆凡
 */
public class ConcurrentHashMapTest {

	private final ConcurrentHashMap<Integer, Integer> hashMap = new ConcurrentHashMap<>();
	
	@Test
	public void test() {
		hashMap.put(1, 2);
	}
	
	@After
	public void after() {
		hashMap.forEach((k, v) -> System.err.println(k + " --- " + v));
	}

}
