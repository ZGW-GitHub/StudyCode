package com.code;

/**
 * @author 愆凡
 * @date 2020/5/16 周六 21:17
 */
public class Demo {

	public static void main(String[] args) {

		String s1 = "Test";
		String s2 = "Test";

		System.out.println(s1.intern());
		System.out.println(s2.intern());

	}

}
