package com.code.thread.nn.threadpool.basic.ss.completable.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author 愆凡
 * @date 2020/5/23 10:21 上午
 */
public class ApiThenComposeTestA {
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
}
