package com.code.thread.nn.threadpool.completionservice;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author 愆凡
 * @date 2020/5/11 9:14 上午
 */
@SuppressWarnings("all")
public class CompletionServiceTest {

	private final ExecutorService executorService = Executors.newFixedThreadPool(4);
	private final CompletionService<Integer> executorCompletionService = new ExecutorCompletionService<Integer>(executorService);

	private final List<Future<Integer>> futures = new ArrayList<>();

	@Test
	public void executorServiceTest() throws ExecutionException, InterruptedException {
		futures.add(executorService.submit(() -> task(60)));
		futures.add(executorService.submit(() -> task(6)));
		futures.add(executorService.submit(() -> task(4)));
		futures.add(executorService.submit(() -> task(2)));

		long before = System.currentTimeMillis();
		// 遍历 Future list，通过 get() 方法获取每个 future 结果
		for (Future<Integer> future : futures) {
			Integer result = future.get();
			TimeUnit.SECONDS.sleep(30);

			System.out.println(result);
		}
		// 60+30+30+30+30=180
		System.err.println((System.currentTimeMillis() - before) / 1000);
	}

	@Test
	public void completionServiceTest() throws InterruptedException, ExecutionException {
		futures.add(executorCompletionService.submit(() -> task(60)));
		futures.add(executorCompletionService.submit(() -> task(6)));
		futures.add(executorCompletionService.submit(() -> task(4)));
		futures.add(executorCompletionService.submit(() -> task(2)));

		long before = System.currentTimeMillis();
		// 遍历 Future list，通过 get() 方法获取每个 future 结果
		for (Future<Integer> ignored : futures) {
			Integer result = executorCompletionService.take().get();
			TimeUnit.SECONDS.sleep(30);

			System.out.println(result);
		}
		// 32+30+30+30=122
		System.err.println((System.currentTimeMillis() - before) / 1000);
	}

	public Integer task(Integer sleep) {
		try {
			TimeUnit.SECONDS.sleep(sleep);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		return sleep;
	}

}
