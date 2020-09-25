package com.code.thread.nn.threadpool.executor.threadpoll.executor;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * invokeAny(callableList) ：
 * 提交了多个 Callable ，等待其中一个完成，其余的就会退出工作。（注意：有可能有两个线程几乎同时完成任务，但只会返回其中的一个的工作结果）
 *
 * @author 愆凡
 */
public class F_Executor_Api_invokeAny {

	public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {

		testInvokeAny();

	}

	private static void testInvokeAny() throws ExecutionException, InterruptedException, TimeoutException {

		ExecutorService executorService = Executors.newFixedThreadPool(10);

		List<Callable<Integer>> callableList = IntStream.range(0, 5).boxed().map(
				integer -> (Callable<Integer>) () -> {
					TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(10));
					System.out.println(Thread.currentThread().getName() + " : " + integer);
					return integer;
				}
		).collect(Collectors.toList());


//        Integer invokeAny = executorService.invokeAny(callableList, 2, TimeUnit.SECONDS);
		Integer invokeAny = executorService.invokeAny(callableList);

		System.out.println("---------------------> finish !");

		System.out.println(invokeAny);

	}

}
