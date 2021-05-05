package com.code.thread.nn.threadpool.basic.ss.completable.future;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author 愆凡
 * @date 2020/5/23 10:21 上午
 */
public class ApiThenComposeTest {

	public final CompletableFuture<Integer> futureData = CompletableFuture.supplyAsync(RunTest::getData);
	public final CompletableFuture<String> futureStringData = CompletableFuture.supplyAsync(RunTest::getStringData);
	public final CompletableFuture<Integer> futureException = CompletableFuture.supplyAsync(RunTest::throwException);

	@Test
	public void unexceptionTest() throws ExecutionException, InterruptedException {
		CompletableFuture<Integer> future = futureData.thenApply((result) -> {
			System.out.println("执行到 handle 了，result : " + result);
			return result + 100;
		});

		System.out.println("计算结果：" + future.get()); // 200
		System.out.println("计算结果：" + futureData.get()); // 100
	}

	@Test
	public void exceptionTest() throws ExecutionException, InterruptedException {
		CompletableFuture<Integer> future = futureException.thenApply((result) -> {
			System.out.println("执行到 handle 了，result : " + result);
			return result + 100;
		});

		System.out.println("计算结果：" + future.get()); // 异常
		System.out.println("计算结果：" + futureException.get()); // 异常
	}

	public static void main(String[] args) throws ExecutionException, InterruptedException {
		CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> 100);

		CompletableFuture<String> f = future.thenCompose(i -> CompletableFuture.supplyAsync(() -> (i * 10) + ""));

		System.out.println(f.get()); // 1000
	}

	public void demo() throws ExecutionException, InterruptedException {
		CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> 100);

		CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> "abc");

		CompletableFuture<String> f = future.thenCombine(future2, (x, y) -> y + "-" + x);

		System.out.println(f.get()); // abc-100
	}
}
