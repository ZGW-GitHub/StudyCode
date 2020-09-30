package com.code.thread.nn.threadpool.basic.ss.completable.future;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author 愆凡
 * @date 2020/9/30 3:29 下午
 */
public class ThenAcceptBothTest {

	private CompletableFuture<Integer> completableFuture1;
	private CompletableFuture<String> completableFuture2;

	@Test
	public void unexceptionTest() throws ExecutionException, InterruptedException {
		completableFuture1 = CompletableFuture.supplyAsync(RunTest::getData);
		completableFuture2 = CompletableFuture.supplyAsync(RunTest::getStringData);

		CompletableFuture<Void> completableFuture3 = completableFuture1
				.thenAcceptBoth(completableFuture2,
						(result1, result2) -> System.out.println("Result : " + result1 + result2));

		System.out.println("计算结果：" + completableFuture1.get()); // 100
		System.out.println("计算结果：" + completableFuture2.get()); // 100
		System.out.println("计算结果：" + completableFuture3.get()); // null
	}

	@Test
	public void exceptionTest() throws ExecutionException, InterruptedException {
		completableFuture1 = CompletableFuture.supplyAsync(RunTest::throwException);
		completableFuture2 = CompletableFuture.supplyAsync(RunTest::getStringData);

		CompletableFuture<Void> completableFuture3 = completableFuture1
				.thenAcceptBoth(completableFuture2,
						(result1, result2) -> System.out.println("Result : " + result1 + result2));

		System.out.println("计算结果：" + completableFuture3.get()); // 异常
		System.out.println("计算结果：" + completableFuture2.get()); // 异常
		System.out.println("计算结果：" + completableFuture1.get()); // 异常
	}

}
