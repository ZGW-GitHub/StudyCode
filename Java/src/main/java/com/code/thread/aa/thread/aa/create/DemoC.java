package com.code.thread.aa.thread.aa.create;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 通过创建类实现 Callable 接口，来创建线程
 *
 * @author 愆凡
 */
public class DemoC {

	public static void main(String[] args) throws ExecutionException, InterruptedException {

		FutureTask<String> futureTask = new FutureTask<>(new MyCallable());
		// 实际还是以 Callable 来创建并启动线程
		new Thread(futureTask).start();
		futureTask.get();

	}

	private static class MyCallable implements Callable<String> {
		@Override
		public String call() throws Exception {
			// 线程需要执行的业务逻辑
			return null;
		}
	}

}
