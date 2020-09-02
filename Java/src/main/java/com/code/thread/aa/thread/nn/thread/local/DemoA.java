package com.code.thread.aa.thread.nn.thread.local;

/**
 * @author 愆凡
 * @date 2020/7/10 4:02 下午
 */
public class DemoA {
	public static void main(String[] args) {

		new Thread(() -> {
			ThreadLocal<String> threadLocalOne = new ThreadLocal<>();
			ThreadLocal<Integer> threadLocalTwo = new ThreadLocal<>();

			threadLocalOne.set("test");
			threadLocalTwo.set(100);

		}).start();

	}
}
