package com.code.thread.nn.threadpool.executor.threadpool.executor;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.lang.NonNull;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * 定义异常处理器，并为 ExecutorService 设置。
 *
 * @author 愆凡
 */
public class ThreadPoolExecutorTest {

	private final ExecutorService executorService = Executors.newFixedThreadPool(10, new MyThreadFactory());

	/**
	 * 拦截并处理任务执行过程中的异常
	 *
	 * @throws InterruptedException 中断异常
	 */
	@Test
	public void exceptionHandlerTest() throws InterruptedException {
		executorService.execute(() -> {
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});

		IntStream.range(0, 10).boxed().forEach(integer -> executorService.execute(() -> System.out.println(1 / 0)));

		executorService.shutdown();
		Assert.assertTrue(executorService.awaitTermination(10, TimeUnit.SECONDS));

		TimeUnit.SECONDS.sleep(5);
	}

	private static class MyThreadFactory implements ThreadFactory {
		private final AtomicInteger SEQ = new AtomicInteger();

		@Override
		public Thread newThread(@NonNull Runnable runnable) {
			Thread thread = new Thread(runnable);
			thread.setName("MyThread-" + SEQ.getAndIncrement());

			// 设置异常处理程序
			thread.setUncaughtExceptionHandler((athread, cause) -> {
				System.out.println("The Thread : " + athread.getName() + " 执行失败！");
				// 输出堆栈信息
//				cause.printStackTrace();
			});

			return thread;
		}
	}

}
