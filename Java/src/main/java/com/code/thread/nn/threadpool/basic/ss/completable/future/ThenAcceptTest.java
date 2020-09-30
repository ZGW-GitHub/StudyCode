package com.code.thread.nn.threadpool.basic.ss.completable.future;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author 愆凡
 * @date 2020/9/30 3:29 下午
 */
public class ThenAcceptTest {

	private CompletableFuture<Integer> completableFuture;

	@Test
	public void unexceptionTest() throws ExecutionException, InterruptedException {
		completableFuture = CompletableFuture.supplyAsync(RunTest::getData);

		CompletableFuture<Void> completableFuture1 = completableFuture
				.thenAccept((result) -> System.out.println("Result : " + result));

		System.out.println("计算结果：" + completableFuture1.get()); // null
		System.out.println("计算结果：" + this.completableFuture.get()); // 100
	}

	@Test
	public void exceptionTest() throws ExecutionException, InterruptedException {
		completableFuture = CompletableFuture.supplyAsync(RunTest::throwException);

		CompletableFuture<Void> completableFuture1 = completableFuture
				.thenAccept((result) -> System.out.println("Result : " + result));

		System.out.println("计算结果：" + completableFuture1.get()); // 异常
		System.out.println("计算结果：" + completableFuture.get()); // 异常
	}

}
