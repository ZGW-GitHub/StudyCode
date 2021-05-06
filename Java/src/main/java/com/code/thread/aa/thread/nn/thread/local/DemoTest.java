package com.code.thread.aa.thread.nn.thread.local;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.junit.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author 愆凡
 * @date 2020/7/10 4:02 下午
 */
@SuppressWarnings("all")
public class DemoTest {

	@Test
	public void simpleTest() throws InterruptedException {
		ThreadLocal<String> threadLocalOne = new ThreadLocal<>();
		ThreadLocal<Integer> threadLocalTwo = new ThreadLocal<>();

		Thread t1 = new Thread(() -> runTask(threadLocalOne, threadLocalTwo, "T1"));
		Thread t2 = new Thread(() -> runTask(threadLocalOne, threadLocalTwo, "T2"));

		t1.start();
		t2.start();

//		TimeUnit.SECONDS.sleep(3);

		System.out.println(ToStringBuilder.reflectionToString(threadLocalOne));
		System.out.println(ToStringBuilder.reflectionToString(threadLocalTwo));

		Thread.currentThread().join();
	}

	private void runTask(ThreadLocal<String> threadLocalOne, ThreadLocal<Integer> threadLocalTwo, String tName) {
		try {
			threadLocalOne.set(tName + "-1");
			threadLocalOne.set(tName + "-2");
			threadLocalTwo.set(new Random().nextInt());

			TimeUnit.SECONDS.sleep(5);

			System.err.println(threadLocalOne.get());
			System.err.println(threadLocalTwo.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void inheritableThreadLocalTest() {
//		ThreadLocal threadLocal = new ThreadLocal();
		InheritableThreadLocal threadLocal = new InheritableThreadLocal();

		IntStream.range(0, 10).forEach(i -> {
			// 每个线程的序列号，希望在子线程中能够拿到
			threadLocal.set(i);
			// 这里来了一个子线程，我们希望可以访问上面的threadLocal
			new Thread(() -> {
				System.out.println(Thread.currentThread().getName() + ":" + threadLocal.get());
			}).start();

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});

		System.err.println(ToStringBuilder.reflectionToString(threadLocal));
	}

}
