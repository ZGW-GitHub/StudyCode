package com.code.thread.nn.threadpool.basic.nn.futuretask;

import org.junit.Test;

import java.util.concurrent.*;

/**
 * {@link FutureTask} 实现了 {@link Future} 和 {@link Runnable} 接口，它既可以当做 Runnable 提交到线程池，又能当做 Future 获取自己的执行情况
 *
 * @author 愆凡
 * @date 2020-03-24 14:04
 */
public class FutureTaskTest {

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
