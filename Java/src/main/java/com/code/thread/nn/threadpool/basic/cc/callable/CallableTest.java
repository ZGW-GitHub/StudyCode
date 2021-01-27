package com.code.thread.nn.threadpool.basic.cc.callable;

import org.junit.Test;

import java.util.concurrent.*;

/**
 * @author 愆凡
 * @date 2020/5/11 9:14 上午
 */
public class CallableTest {

	private final ExecutorService executor = Executors.newCachedThreadPool();

	@Test
	public void threadPoolTest() throws ExecutionException, InterruptedException {
		Future<String> future = executor.submit(() -> {
			TimeUnit.SECONDS.sleep(3);
			return "OK";
		});

		executor.shutdown();

		System.out.println(future.get()); // OK
	}

}
