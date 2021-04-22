package com.code.basic.math;

import org.junit.Test;

import java.text.DecimalFormat;

/**
 * @author 愆凡
 * @date 2020/12/29 16:53
 */
public class MathTest {

	/**
	 * 测试格式化
	 */
	@Test
	public void formatTest() {
		System.err.println("- 方式1 ------>  " + String.format("%.2f", 0.395));

		// 对于整数部分，若整数部分为0 ：# 表示最多有几位（因此时整数为0所以认为整数没有） ，0 表示必须有几位
		// 对于整数部分，若整数部分不为0 ：无论是 # 或 0 ，整数部分将显示全
		// 对于小数部分：# 表示最多有几位 ，0 表示必须有几位
		System.err.println("- 方式2 ------>  " + new DecimalFormat("0.00").format(0.395));
		System.err.println("- 方式2 ------>  " + new DecimalFormat("0.##").format(0.395));

		System.err.println("- 方式3 ------>  " + new DecimalFormat("#.00").format(0.395));
		System.err.println("- 方式3 ------>  " + new DecimalFormat("0.00").format(0.395));
	}

}
