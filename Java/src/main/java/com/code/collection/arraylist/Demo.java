package com.code.collection.arraylist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author 愆凡
 * @date 2020/9/3 11:41 上午
 */
public class Demo {
	public static void main(String[] args) {

		List<Integer> integers = Arrays.asList(1000, 2000, 3000, 4000, 5000, 6000, 7000, 8000, 9000, 10000, 11000);

		List<Integer> push = new ArrayList<>();
		for (Integer i : integers) {
			push.add(i);

			if (push.size() >= 5) {
				List<Integer> dopush = new ArrayList<>(push);
				push.clear();

				doprint(dopush);
			}
		}

		if (push.size() > 0) {
			doprint(push);
		}

	}

	private static void doprint(List<Integer> dopush) {
		System.out.println(Arrays.toString(dopush.toArray()));
	}
}
