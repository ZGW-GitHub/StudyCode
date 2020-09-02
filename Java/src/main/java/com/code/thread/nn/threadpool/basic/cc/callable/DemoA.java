package com.code.thread.nn.threadpool.basic.cc.callable;

import java.util.concurrent.*;

/**
 * @author 愆凡
 * @date 2020/5/11 9:14 上午
 */
public class DemoA {
	public static void main(String[] args) throws ExecutionException, InterruptedException {

		Callable<String> callable = new Callable<String>() {
			@Override
			public String call() throws Exception {
				return "This is Callable !";
			}
		};

		ExecutorService executorService = Executors.newCachedThreadPool();
		Future<String> future = executorService.submit(callable);
		System.out.println(future.get());

		executorService.shutdown();

	}
}
