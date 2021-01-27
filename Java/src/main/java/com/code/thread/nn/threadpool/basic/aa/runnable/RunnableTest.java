package com.code.thread.nn.threadpool.basic.aa.runnable;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.*;

/**
 * @author 愆凡
 * @date 2020/5/11 9:14 上午
 */
@Slf4j
public class RunnableTest {

	@Test
	public void threadPoolTest() throws ExecutionException, InterruptedException {
		final ExecutorService executor = Executors.newCachedThreadPool();

		Future<?> future = executor.submit(() -> {
			try {
				TimeUnit.SECONDS.sleep(3);
				System.out.println("OK");
			} catch (InterruptedException e) {
				log.error("Exception : ", e);
			}
		});

		executor.shutdown();

		System.out.println(future.get()); // null
	}

	@Test
	public void threadTest() throws InterruptedException {
		new Thread(() -> {
			try {
				TimeUnit.SECONDS.sleep(3);
				System.out.println("OK");
			} catch (InterruptedException e) {
				log.error("Exception : ", e);
			}
		}).start();

		Thread.currentThread().join();
	}

}
