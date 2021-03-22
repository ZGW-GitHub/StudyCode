package com.code.thread.aa.thread.nn.thread.local;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * @author 愆凡
 * @date 2020/7/10 4:02 下午
 */
@SuppressWarnings("all")
public class DemoTest {

	@Test
	public void simpleTest() throws InterruptedException {
		ThreadLocal<String> threadLocal = new ThreadLocal<>();

		new Thread(() -> {
			try {
				threadLocal.set("one");
				
				TimeUnit.SECONDS.sleep(5);

				System.err.println(threadLocal.get());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();

		new Thread(() -> {
			try {
				threadLocal.set("two");

				TimeUnit.SECONDS.sleep(5);

				System.err.println(threadLocal.get());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();

		Thread.currentThread().join();
	}
}
