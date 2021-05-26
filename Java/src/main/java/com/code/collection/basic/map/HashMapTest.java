package com.code.collection.basic.map;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * @author 愆凡
 * @date 2020/8/14 11:29 上午
 */
public class HashMapTest {

	@Test
	@SuppressWarnings("all")
	public void test() {
		final Map<Integer, Integer> map = new HashMap<>(0);

		IntStream.range(1, 6).forEach(i -> {
			map.put(i, i);
			System.err.println("put i : " + i);
		});
	}

}
