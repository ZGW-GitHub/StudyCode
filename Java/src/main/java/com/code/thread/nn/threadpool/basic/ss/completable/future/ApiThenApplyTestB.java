package com.code.thread.nn.threadpool.basic.ss.completable.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author 愆凡
 * @date 2020/5/21 5:25 下午
 */
public class ApiThenApplyTestB {
	public static void main(String[] args) throws ExecutionException, InterruptedException {

		CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(RunTest::throwException);

		CompletableFuture<Integer> future2 = future1.thenApply((result) -> {
			System.out.println("执行到 whenComplete 了，result : " + result);
			return result + 100;
		});

		System.out.println("future2 result：" + future2.get());

	}
}
