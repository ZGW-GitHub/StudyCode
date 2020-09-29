package com.code.thread.nn.threadpool.basic.ee.future;

import org.junit.Test;

import java.util.concurrent.*;

/**
 * @author 愆凡
 * @date 2020-03-24 13:41
 **/
public class FutureTest {

	private final ExecutorService executor = Executors.newCachedThreadPool();

	@Test
	public void threadPoolTest() throws ExecutionException, InterruptedException {
		Future<String> future = executor.submit(() -> {
			TimeUnit.SECONDS.sleep(3);
			return "OK";
		});

		System.out.println(future.get());

		executor.shutdown();
	}
}
