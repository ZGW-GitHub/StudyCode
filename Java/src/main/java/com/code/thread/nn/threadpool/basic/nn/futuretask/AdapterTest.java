package com.code.thread.nn.threadpool.basic.nn.futuretask;

import org.junit.Test;

import java.util.concurrent.*;

/**
 * @author 愆凡
 * @date 2020-03-24 14:04
 */
public class AdapterTest {

	private final ExecutorService executor = Executors.newCachedThreadPool();

	@Test
	public void callableAdapterTest() throws ExecutionException, InterruptedException {
		FutureTask<String> futureTask = new FutureTask<>(() -> "OK");

		Future<?> future = executor.submit(futureTask);
		executor.shutdown();

		TimeUnit.SECONDS.sleep(2);

		System.out.println(future.get()); // null
		System.out.println(futureTask.get()); // OK
	}

	@Test
	public void runnableAdapterTest() throws InterruptedException, ExecutionException {
		FutureTask<String> futureTask =
				new FutureTask<>(() -> System.out.println("-"), "Test");

		Future<?> future = executor.submit(futureTask);
		executor.shutdown();

		TimeUnit.SECONDS.sleep(2);

		System.out.println(future.get()); // null
		System.out.println(futureTask.get()); // Test
	}

}
