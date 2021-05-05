package com.code.thread.nn.threadpool.basic.ss.completable.future;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author 愆凡
 * @date 2020/9/30 3:29 下午
 */
public class ThenAcceptBothTest {

	public final CompletableFuture<Integer> futureData = CompletableFuture.supplyAsync(RunTest::getData);
	public final CompletableFuture<String> futureStringData = CompletableFuture.supplyAsync(RunTest::getStringData);
	public final CompletableFuture<Integer> futureException = CompletableFuture.supplyAsync(RunTest::throwException);

	@Test
	public void unexceptionTest() throws ExecutionException, InterruptedException {
		CompletableFuture<Void> completableFuture3 = futureData
				.thenAcceptBoth(futureStringData, (result1, result2) -> System.out.println("Result : " + result1 + result2));

		System.out.println("计算结果：" + futureData.get()); // 100
		System.out.println("计算结果：" + futureStringData.get()); // 100
		System.out.println("计算结果：" + completableFuture3.get()); // null
	}

	@Test
	public void exceptionTest() throws ExecutionException, InterruptedException {
		CompletableFuture<Void> completableFuture3 = futureException
				.thenAcceptBoth(futureStringData, (result1, result2) -> System.out.println("Result : " + result1 + result2));

		System.out.println("计算结果：" + completableFuture3.get()); // 异常
		System.out.println("计算结果：" + futureStringData.get()); // 异常
		System.out.println("计算结果：" + futureException.get()); // 异常
	}

}
