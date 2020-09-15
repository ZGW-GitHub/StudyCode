package com.code.thread.nn.threadpool.basic.ss.completable.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author 愆凡
 * @date 2020/5/21 5:19 下午
 */
public class ApiHandleTestB {
	public static void main(String[] args) throws ExecutionException, InterruptedException {

		CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(RunTest::throwException);

		CompletableFuture<Integer> future2 = future1.handle((result, excetion) -> {
			System.out.println("执行到 handle 了，result : " + result);
			System.out.println("执行到 handle 了，exception : " + (excetion == null ? "无异常" : excetion.getClass()));
			if (result == null) {
				return 100;
			}
			return result + 100;
		});

		System.out.println("future2 result：" + future2.get());

	}
}