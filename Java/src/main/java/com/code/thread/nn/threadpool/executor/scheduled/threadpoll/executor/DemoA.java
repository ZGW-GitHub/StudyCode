package com.code.thread.nn.threadpool.executor.scheduled.threadpoll.executor;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author NotUpToYou
 */
public class DemoA {

	private static final ScheduledThreadPoolExecutor SCHEDULED_THREAD_POOL_EXECUTOR = new ScheduledThreadPoolExecutor(2);


	public static void main(String[] args) throws ExecutionException, InterruptedException {

		testScheduleWithRunnable();
		testScheduleWithCallable();
		testScheduleAtFixedRate();
		testScheduleWithFixedDelay();

	}

	// 一次性延迟任务
	private static void testScheduleWithRunnable() throws ExecutionException, InterruptedException {

		ScheduledFuture<?> future = SCHEDULED_THREAD_POOL_EXECUTOR.schedule(() -> System.out.println("执行了！"), 2, TimeUnit.SECONDS);
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(future.get());

	}

	// 一次性延迟任务
	private static void testScheduleWithCallable() throws ExecutionException, InterruptedException {

		ScheduledFuture<?> future = SCHEDULED_THREAD_POOL_EXECUTOR.schedule(() -> {
			System.out.println("执行了！");
			return "执行了";
		}, 2, TimeUnit.SECONDS);
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(future.get());

	}

	// 周期任务
	// 任务工作时间大于循环时间，下一次循环会延迟执行
	private static void testScheduleAtFixedRate() {

		SCHEDULED_THREAD_POOL_EXECUTOR.scheduleAtFixedRate(() -> {
			try {
				System.out.println("工作 ：" + System.currentTimeMillis());
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}, 3, 5, TimeUnit.SECONDS);

	}

	// 延迟任务（也是周期的）
	// 无论任务执行多长时间，下一次循环都要等待 delay 秒，再执行
	private static void testScheduleWithFixedDelay() {

		SCHEDULED_THREAD_POOL_EXECUTOR.scheduleWithFixedDelay(() -> {
			try {
				System.out.println("工作 ：" + System.currentTimeMillis());
				TimeUnit.SECONDS.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}, 3, 5, TimeUnit.SECONDS);

	}

}
