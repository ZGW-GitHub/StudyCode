package com.code.thread.nn.threadpool.basic.ss.completable.future;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author 愆凡
 * @date 2020/9/29 4:39 下午
 */
public class WhenCompleteTest {

	public final CompletableFuture<Integer> futureData = CompletableFuture.supplyAsync(RunTest::getData);
	public final CompletableFuture<Integer> futureException = CompletableFuture.supplyAsync(RunTest::throwException);

	@Test
	public void unexceptionTest() throws ExecutionException, InterruptedException {
		CompletableFuture<Integer> future = futureData.whenComplete((result, exception) -> {
			System.out.println("Result : " + result);
			System.out.println("Exception : " + (exception == null ? "无异常" : exception.getClass()));
		}).exceptionally(exception -> {
			System.out.println("进行异常的处理...");
			return 6;
		});

		System.out.println(futureData.get()); // 100
		System.out.println(future.get()); // 100
	}

	@Test
	public void exceptionTest() throws ExecutionException, InterruptedException {
		CompletableFuture<Integer> future = futureException.whenComplete((result, exception) -> {
			System.out.println("Result : " + result);
			System.out.println("Exception : " + (exception == null ? "无异常" : exception.getClass()));
		}).exceptionally(exception -> {
			System.out.println("进行异常的处理...");

			return 6;
		});

//		System.out.println(futureException.get()); // 异常
		System.out.println(future.get()); // 6
	}

	@Test
	public void exceptionTestTwo() throws ExecutionException, InterruptedException {
		CompletableFuture<Integer> future1 = futureException.whenComplete((result, exception) -> {
			System.out.println("Result : " + result);
			System.out.println("Exception : " + (exception == null ? "无异常" : exception.getClass()));
		});

		CompletableFuture<Integer> future2 = future1.exceptionally(exception -> {
			System.out.println("进行异常的处理...");
			return 6;
		});

		System.out.println(future2.get()); // 6
//		System.out.println(future1.get()); // 异常
//		System.out.println(futureException.get()); // 异常
	}

}
