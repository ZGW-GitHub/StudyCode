package com.code.util.string;

import org.junit.Test;

/**
 * @author 愆凡
 * @date 2020/8/14 11:30 上午
 */
@SuppressWarnings("all")
public class DemoTest {

	/**
	 * 测试格式化
	 */
	@Test
	public void formatTest() {
		System.out.println(String.format("%.3f", 20 * 1.0 / 3));
	}

	/**
	 * 测试截取
	 */
	@Test
	public void subTest() {
		String sub = "测试截取1";
		String subed = sub.length() > 4 ? sub.substring(0, 4) : sub;
		System.out.println(subed);
	}

}
