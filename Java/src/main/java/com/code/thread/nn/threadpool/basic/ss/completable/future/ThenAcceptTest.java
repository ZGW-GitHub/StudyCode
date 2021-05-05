package com.code.thread.nn.threadpool.basic.ss.completable.future;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author 愆凡
 * @date 2020/9/30 3:29 下午
 */
public class ThenAcceptTest {

	public final CompletableFuture<Integer> futureData = CompletableFuture.supplyAsync(RunTest::getData);
	public final CompletableFuture<Integer> futureException = CompletableFuture.supplyAsync(RunTest::throwException);

	@Test
	public void unexceptionTest() throws ExecutionException, InterruptedException {
		CompletableFuture<Void> future = futureData
				.thenAccept((result) -> System.out.println("Result : " + result));

		System.out.println("计算结果：" + future.get()); // null
		System.out.println("计算结果：" + this.futureData.get()); // 100
	}

	@Test
	public void exceptionTest() throws ExecutionException, InterruptedException {
		CompletableFuture<Void> future = futureException
				.thenAccept((result) -> System.out.println("Result : " + result));

		System.out.println("计算结果：" + future.get()); // 异常
		System.out.println("计算结果：" + futureException.get()); // 异常
	}

}
