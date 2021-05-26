package com.code.thread.nn.threadpool.executor.forkjoinpool;

import org.junit.Test;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * 实现斐波那契数列
 *
 * @author 愆凡
 * @date 2020/5/21 3:18 下午
 */
public class FibonacciTest {

	private final ForkJoinPool forkJoinPool = new ForkJoinPool(4); // 最大并发数4

	@Test
	public void test() {
		Fibonacci fibonacci = new Fibonacci(20);

		long startTime = System.currentTimeMillis();

		Integer result = forkJoinPool.invoke(fibonacci);

		long endTime = System.currentTimeMillis();

		System.out.println("Fork/join sum: " + result + " in " + (endTime - startTime) + " ms.");
	}

	public static class Fibonacci extends RecursiveTask<Integer> {
		final int n;

		public Fibonacci(int n) {
			this.n = n;
		}

		@Override
		protected Integer compute() {
			if (n <= 1) {
				return n;
			}
			Fibonacci f1 = new Fibonacci(n - 1);
			f1.fork();
			Fibonacci f2 = new Fibonacci(n - 2);
			return f2.compute() + f1.join();
		}
	}

}
