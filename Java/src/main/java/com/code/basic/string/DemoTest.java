package com.code.basic.string;

import org.junit.Test;

/**
 * @author 愆凡
 * @date 2020/8/14 11:30 上午
 */
@SuppressWarnings("all")
public class DemoTest {

	/**
	 * 测试截取
	 */
	@Test
	public void subTest() {
		String sub = "测试截取1";
		String subed = sub.length() > 4 ? sub.substring(0, 4) : sub;
		System.out.println(subed);
	}

	@Test
	public void valueOfTest() {
		int num = -123;
		System.out.println(String.valueOf(num));
	}

}
