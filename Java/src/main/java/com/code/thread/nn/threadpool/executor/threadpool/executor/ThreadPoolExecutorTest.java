package com.code.thread.nn.threadpool.executor.threadpool.executor;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.lang.NonNull;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * 定义异常处理器，并为 ExecutorService 设置。
 *
 * @author 愆凡
 */
@Slf4j
public class ThreadPoolExecutorTest {

	private final ExecutorService executorService = new ThreadPoolExecutor(
			3, 5, 60, TimeUnit.SECONDS,
			new ArrayBlockingQueue<>(10), new MyThreadFactory(),
			(runnable, executor) -> {
				log.warn("线程池已满,任务:" + runnable + ",已被拒绝");
				// 处理被拒绝的任务
			});

	/**
	 * 拦截并处理任务执行过程中的异常
	 *
	 * @throws InterruptedException 中断异常
	 */
	@Test
	public void exceptionHandlerTest() throws InterruptedException {
		executorService.submit(() -> {
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});

		IntStream.range(0, 10).boxed().forEach(integer -> executorService.submit(() -> System.out.println(1 / 0)));

		executorService.shutdown();
		Assert.assertTrue(executorService.awaitTermination(10, TimeUnit.SECONDS));

		TimeUnit.SECONDS.sleep(5);
	}

	private static class MyThreadFactory implements ThreadFactory {
		private final AtomicInteger threadNum = new AtomicInteger();

		@Override
		public Thread newThread(@NonNull Runnable runnable) {
			Thread thread = new Thread(runnable);
			thread.setName("MyThread-" + threadNum.getAndIncrement());
			// 设置异常处理程序
			thread.setUncaughtExceptionHandler((athread, cause) ->
					log.error("The Thread : " + athread.getName() + " 执行失败！", cause));

			return thread;
		}
	}

}
