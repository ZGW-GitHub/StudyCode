package com.code.thread.nn.threadpool.executors.forkjoinpool;

import com.code.thread.nn.threadpool.executor.forkjoinpool.FibonacciTest;
import org.junit.Test;

import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;

/**
 * 实现斐波那契数列
 *
 * @author 愆凡
 * @date 2021/4/29 14:43
 */
public class ForkJoinPoolTest {

	private final ForkJoinPool forkJoinPool = (ForkJoinPool) Executors.newWorkStealingPool();

	@Test
	public void fibonacciTest() {
		FibonacciTest.Fibonacci fibonacci = new FibonacciTest.Fibonacci(20);

		long startTime = System.currentTimeMillis();

		Integer result = forkJoinPool.invoke(fibonacci);

		long endTime = System.currentTimeMillis();

		System.out.println("Fork/join sum: " + result + " in " + (endTime - startTime) + " ms.");
	}

}
