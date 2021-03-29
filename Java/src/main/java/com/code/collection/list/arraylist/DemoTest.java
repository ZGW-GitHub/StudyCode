package com.code.collection.list.arraylist;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author 愆凡
 * @date 2020/9/3 11:41 上午
 */
public class DemoTest {

	@Test
	public void retainAllTest() {
		List<String> str1 = new ArrayList<>();
		str1.add("a");
		str1.add("b");
		str1.add("c");

		List<String> str2 = new ArrayList<>();
		str2.add("d");
		str2.add("b");
		str2.add("f");

		System.out.println(Arrays.toString(str1.toArray()));
		System.out.println(Arrays.toString(str2.toArray()));

		System.out.println(str1.retainAll(str2));

		System.out.println(Arrays.toString(str1.toArray()));
		System.out.println(Arrays.toString(str2.toArray()));
	}
	
	@Test
	public void reverseTest() {
		List<String> strs = new ArrayList<>();
		strs.add("a");
		strs.add("b");
		strs.add("c");
		
		strs.forEach(System.err::println);
		
		Collections.reverse(strs);

		strs.forEach(System.err::println);
	}

}
