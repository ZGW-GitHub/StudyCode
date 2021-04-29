package com.code.thread.nn.threadpool.executors.threadpool.executor;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author 愆凡
 */
public class FixedThreadPoolTest {

	private final ExecutorService executorService = Executors.newFixedThreadPool(10);
	
	@Test
	public void test() throws InterruptedException {
		// 0
		System.out.println(((ThreadPoolExecutor) executorService).getActiveCount());

		IntStream.range(0, 100).boxed().forEach(i -> executorService.execute(() -> {
			try {
				TimeUnit.SECONDS.sleep(1);
				System.out.println(Thread.currentThread().getName() + " is ok !");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}));
		TimeUnit.SECONDS.sleep(1);

		// 10
		System.out.println(((ThreadPoolExecutor) executorService).getActiveCount());

	}

}
