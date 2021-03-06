package com.code.thread.aa.thread.cc.methods;

import org.junit.Test;

/**
 * 让掉当前线程 CPU 的时间片，使正在运行中的线程重新变成就绪状态，并重新竞争 CPU 的调度权。
 * 它可能会获取到，也有可能被其他线程获取到。
 *
 * @author 愆凡
 */
@SuppressWarnings("all")
public class YieldTest {

	@Test
	public void test(String[] args) {
		new Thread(() -> {
			for (int i = 0; i <= 100; i++) {
				System.out.println(i);
				if (i == 50) {
					Thread.yield();
				}
			}
		}).start();

		new Thread(() -> {
			for (int i = 0; i <= 10; i++) {
				System.out.println("======");
			}
		}).start();
	}

}
