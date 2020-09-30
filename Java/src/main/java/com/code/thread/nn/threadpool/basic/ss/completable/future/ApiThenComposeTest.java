package com.code.thread.nn.threadpool.basic.ss.completable.future;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author 愆凡
 * @date 2020/5/23 10:21 上午
 */
public class ApiThenComposeTest {

	private CompletableFuture<Integer> completableFuture;

	@Test
	public void unexceptionTest() throws ExecutionException, InterruptedException {
		completableFuture = CompletableFuture.supplyAsync(RunTest::getData);

		CompletableFuture<Integer> completableFuture1 = completableFuture.thenApply((result) -> {
			System.out.println("执行到 handle 了，result : " + result);
			return result + 100;
		});

		System.out.println("计算结果：" + completableFuture1.get()); // 200
		System.out.println("计算结果：" + completableFuture.get()); // 100
	}

	@Test
	public void exceptionTest() throws ExecutionException, InterruptedException {
		completableFuture = CompletableFuture.supplyAsync(RunTest::throwException);

		CompletableFuture<Integer> completableFuture1 = completableFuture.thenApply((result) -> {
			System.out.println("执行到 handle 了，result : " + result);
			return result + 100;
		});

		System.out.println("计算结果：" + completableFuture1.get()); // 异常
		System.out.println("计算结果：" + completableFuture.get()); // 异常
	}

	public static void main(String[] args) throws ExecutionException, InterruptedException {

		CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
			return 100;
		});
		CompletableFuture<String> f = future.thenCompose(i -> {
			return CompletableFuture.supplyAsync(() -> {
				return (i * 10) + "";
			});
		});
		System.out.println(f.get()); //1000

	}

	public void demo() throws ExecutionException, InterruptedException {
		CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> 100);

		CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> "abc");

		CompletableFuture<String> f = future.thenCombine(future2, (x, y) -> y + "-" + x);

		System.out.println(f.get()); //abc-100
	}
}
