package com.code.thread.nn.threadpool.basic.ss.completable.future;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author 愆凡
 * @date 2020/9/29 4:39 下午
 */
public class WhenCompleteTest {

	private CompletableFuture<Integer> completableFuture;

	@Test
	public void unexceptionTest() throws ExecutionException, InterruptedException {
		completableFuture = CompletableFuture.supplyAsync(RunTest::getData);

		CompletableFuture<Integer> completableFuture1 = completableFuture.whenComplete((result, exception) -> {
			System.out.println("Result : " + result);
			System.out.println("Exception : " + (exception == null ? "无异常" : exception.getClass()));
		}).exceptionally(exception -> {
			System.out.println("进行异常的处理...");
			return 6;
		});

		System.out.println(completableFuture.get()); // 100
		System.out.println(completableFuture1.get()); // 100
	}

	@Test
	public void exceptionTest() throws ExecutionException, InterruptedException {
		completableFuture = CompletableFuture.supplyAsync(RunTest::throwException);

		CompletableFuture<Integer> completableFuture1 = completableFuture.whenComplete((result, exception) -> {
			System.out.println("Result : " + result);
			System.out.println("Exception : " + (exception == null ? "无异常" : exception.getClass()));
		}).exceptionally(exception -> {
			System.out.println("进行异常的处理...");

			return 6;
		});

//		System.out.println(completableFuture.get()); // 异常
		System.out.println(completableFuture1.get()); // 6
	}

	@Test
	public void exceptionTestTwo() throws ExecutionException, InterruptedException {
		completableFuture = CompletableFuture.supplyAsync(RunTest::throwException);

		CompletableFuture<Integer> completableFuture1 = completableFuture.whenComplete((result, exception) -> {
			System.out.println("Result : " + result);
			System.out.println("Exception : " + (exception == null ? "无异常" : exception.getClass()));
		});

		CompletableFuture<Integer> completableFuture2 = completableFuture1.exceptionally(exception -> {
			System.out.println("进行异常的处理...");
			return 6;
		});

		System.out.println(completableFuture2.get()); // 6
//		System.out.println(completableFuture1.get()); // 异常
//		System.out.println(completableFuture.get()); // 异常
	}

}
