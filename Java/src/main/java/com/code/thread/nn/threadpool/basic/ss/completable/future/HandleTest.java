package com.code.thread.nn.threadpool.basic.ss.completable.future;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author 愆凡
 * @date 2020/5/21 5:19 下午
 */
public class HandleTest {

	private CompletableFuture<Integer> completableFuture;

	@Test
	public void unexceptionTest() throws ExecutionException, InterruptedException {
		completableFuture = CompletableFuture.supplyAsync(RunTest::getData);

		CompletableFuture<Integer> completableFuture1 = completableFuture.handle((result, exception) -> {
			System.out.println("Result : " + result);
			System.out.println("Exception : " + (exception == null ? "无异常" : exception.getClass()));
			return result + 100;
		});

		System.out.println("计算结果：" + completableFuture1.get()); // 200
		System.out.println("计算结果：" + this.completableFuture.get()); // 100
	}

	@Test
	public void exceptionTest() throws ExecutionException, InterruptedException {
		completableFuture = CompletableFuture.supplyAsync(RunTest::throwException);

		CompletableFuture<Integer> completableFuture1 = completableFuture.handle((result, exception) -> {
			System.out.println("Result : " + result);
			System.out.println("Exception : " + (exception == null ? "无异常" : exception.getClass()));

			return result + 100;
		});

		System.out.println("计算结果：" + completableFuture1.get()); // 异常
		System.out.println("计算结果：" + completableFuture.get()); // 异常
	}

}
