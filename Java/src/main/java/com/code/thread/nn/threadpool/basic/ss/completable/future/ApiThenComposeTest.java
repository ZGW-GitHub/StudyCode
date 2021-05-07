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

	/**
	 * {@link CompletableFuture#whenComplete} 对 CompletableFuture 的结果进行消费
	 */
	@Test
	public void whenCompleteTestOne() throws ExecutionException, InterruptedException {
//		CompletableFuture<Integer> future = futureException.whenComplete((result, exception) -> {
		CompletableFuture<Integer> future = futureData.whenComplete((result, exception) -> {
			System.out.println("Result : " + result);
			System.out.println("Exception : " + (exception == null ? "无异常" : exception.getClass()));
		}).exceptionally(exception -> {
			System.out.println("进行异常的处理...");
			return 6;
		});

		System.out.println(futureData.get()); // 100
		System.out.println(future.get()); // 100 、6
	}

	@Test
	public void whenCompleteTestTwo() throws ExecutionException, InterruptedException {
		CompletableFuture<Integer> future1 = futureException.whenComplete((result, exception) -> {
			System.out.println("Result : " + result);
			System.out.println("Exception : " + (exception == null ? "无异常" : exception.getClass()));
		});

		CompletableFuture<Integer> future2 = future1.exceptionally(exception -> {
			System.out.println("进行异常的处理...");
			return 6;
		});

		System.out.println(future2.get()); // 6
	}

	@Test
	public void thenApplyTest() throws ExecutionException, InterruptedException {
		CompletableFuture<Integer> future = futureData.thenApply((result) -> {
			System.out.println("执行到 handle 了，result : " + result);
			return result + 100;
		});

		System.out.println("计算结果：" + future.get()); // 200
		System.out.println("计算结果：" + futureData.get()); // 100
	}

	@Test
	public void handleTest() throws ExecutionException, InterruptedException {
		CompletableFuture<Integer> future = futureData.handle((result, exception) -> {
			System.out.println("Result : " + result);
			System.out.println("Exception : " + (exception == null ? "无异常" : exception.getClass()));
			return result + 100;
		});

		System.out.println("计算结果：" + future.get()); // 200
		System.out.println("计算结果：" + futureData.get()); // 100
	}

	@Test
	public void thenComposeTest() throws ExecutionException, InterruptedException {
		CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> 100);

		CompletableFuture<String> f = future.thenCompose(i -> CompletableFuture.supplyAsync(() -> (i * 10) + ""));

		System.out.println(f.get()); // 1000
	}

	@Test
	public void thenCombineTest() throws ExecutionException, InterruptedException {
		CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> 100);

		CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> "abc");

		CompletableFuture<String> f = future.thenCombine(future2, (x, y) -> y + "-" + x);

		System.out.println(f.get()); // abc-100
	}

	@Test
	public void thenAcceptTest() throws ExecutionException, InterruptedException {
		CompletableFuture<Void> future = futureData
				.thenAccept((result) -> System.out.println("Result : " + result));

		System.out.println("计算结果：" + future.get()); // null
		System.out.println("计算结果：" + this.futureData.get()); // 100
	}

	@Test
	public void thenAcceptBothTest() throws ExecutionException, InterruptedException {
		CompletableFuture<Void> completableFuture3 = futureData
				.thenAcceptBoth(futureStringData, (result1, result2) -> System.out.println("Result : " + result1 + result2));

		System.out.println("计算结果：" + futureData.get()); // 100
		System.out.println("计算结果：" + futureStringData.get()); // 100
		System.out.println("计算结果：" + completableFuture3.get()); // null
	}

}
