package com.code.thread.nn.threadpool.executor.scheduled.threadpool.executor;

import org.junit.Test;

import java.time.LocalTime;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author 愆凡
 */
public class ScheduledThreadPoolExecutorTest {

	private final ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(2);

	/**
	 * 一次性延迟任务
	 */
	@Test
	public void testScheduleWithCallable() throws ExecutionException, InterruptedException {
		ScheduledFuture<?> future = executor.schedule(() -> {
			System.err.println("执行了！");
			return LocalTime.now();
		}, 5, TimeUnit.SECONDS);

		System.err.println("begin : " + LocalTime.now());

		System.err.println("end : " + future.get());
	}

	/**
	 * 周期任务<br />
	 * 若任务工作时间大于循环时间，下一次循环会在任务完成后立即执行<br />
	 * 若任务工作时间小于循环时间，下一次循环会等待 循环时间 - 任务工作时间 后执行
	 */
	@Test
	public void testScheduleAtFixedRate() throws InterruptedException {

		executor.scheduleAtFixedRate(() -> {
			try {
				System.out.println("time ：" + LocalTime.now());
				TimeUnit.SECONDS.sleep(8);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}, 3, 5, TimeUnit.SECONDS);

		System.err.println("begin : " + LocalTime.now());

		Thread.currentThread().join();
	}

	/**
	 * 延迟任务（也是周期的）
	 * 无论任务执行多长时间，下一次循环都要在任务完成后再等待 delay 秒，再执行
	 */
	@Test
	public void testScheduleWithFixedDelay() throws InterruptedException {

		executor.scheduleWithFixedDelay(() -> {
			try {
				System.out.println("time ：" + LocalTime.now());
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}, 3, 5, TimeUnit.SECONDS);

		System.err.println("begin : " + LocalTime.now());

		Thread.currentThread().join();
	}

}
