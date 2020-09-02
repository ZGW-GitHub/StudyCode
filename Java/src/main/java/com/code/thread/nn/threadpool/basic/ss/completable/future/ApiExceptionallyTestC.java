package com.code.thread.nn.threadpool.basic.ss.completable.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author zgw
 * @date 2020/4/21 3:06 下午
 */
public class ApiExceptionallyTestC {
	public static void main(String[] args) throws ExecutionException, InterruptedException {

		CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(RunTest::getMoreData);

		CompletableFuture<Integer> future2 = future1.whenCompleteAsync((result, exception) -> {
			System.out.println("计算已执行完毕，result : " + result);
			System.out.println("计算已执行完毕，exception : " + (exception == null ? "无异常" : exception.getClass()));
		});

		CompletableFuture<Integer> future3 = future2.exceptionally(exception -> {
			System.out.println("计算执行过程中发生了异常，exception : " + exception.getClass());
			return 0;
		});

		System.out.println("future1 result：" + future1.get());
		System.out.println("future2 result：" + future2.get());
		// 原始的计算逻辑不变，exceptionally 返回的新的 CompletableFuture 对象的结果和原始计算逻辑返回的结果一致。
		System.out.println("future3 result：" + future3.get());

	}
}
