package com.code.thread.nn.threadpool.basic.ss.completable.future;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author 愆凡
 * @date 2020/5/21 5:19 下午
 */
public class ThenApplyTest {

	public final CompletableFuture<Integer> futureData = CompletableFuture.supplyAsync(RunTest::getData);
	public final CompletableFuture<Integer> futureException = CompletableFuture.supplyAsync(RunTest::throwException);

	@Test
	public void unexceptionTest() throws ExecutionException, InterruptedException {
		CompletableFuture<Integer> future = futureData.thenApply((result) -> {
			System.out.println("Result : " + result);

			return result + 100;
		});

		System.out.println("计算结果：" + future.get()); // 200
		System.out.println("计算结果：" + futureData.get()); // 100
	}

	@Test
	public void exceptionTest() throws ExecutionException, InterruptedException {
		CompletableFuture<Integer> future = futureException.thenApply((result) -> {
			System.out.println("Result : " + result);

			return result + 100;
		});

		System.out.println("计算结果：" + future.get()); // 异常
		System.out.println("计算结果：" + futureException.get()); // 异常
	}

}
