package com.code.collection.arraylist;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 愆凡
 * @date 2020/9/3 11:41 上午
 */
public class AddDemo {
	public static void main(String[] args) {

		List<String> listA = new ArrayList<>();
		listA.add("test");

		List<String> listB = new ArrayList<>(listA);

		listA.clear();

		listB.forEach(System.out::println); // test
	}
}
