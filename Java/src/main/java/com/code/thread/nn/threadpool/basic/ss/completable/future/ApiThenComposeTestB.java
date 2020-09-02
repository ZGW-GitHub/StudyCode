package com.code.thread.nn.threadpool.basic.ss.completable.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author 愆凡
 * @date 2020/5/23 10:21 上午
 */
public class ApiThenComposeTestB {
	public static void main(String[] args) throws ExecutionException, InterruptedException {

		CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> 100);

		CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> "abc");

		CompletableFuture<String> f =  future.thenCombine(future2, (x,y) -> y + "-" + x);

		System.out.println(f.get()); //abc-100

	}
}
