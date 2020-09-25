package com.code.thread.ss.atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 愆凡
 */
public class Unsafe {


	/**
	 * volatile（关键字）
	 * Count is : 23826618
	 * The time is : 1847
	 * <p>
	 * synchronized（重量级锁）
	 * Count is : 100000000
	 * The time is : 3322
	 * <p>
	 * Lock（显式锁）
	 * Count is : 100000000
	 * The time is : 2258
	 * <p>
	 * AtomicInteger（原子类）
	 * Count is : 100000000
	 * The time is : 2650
	 */


	public static void main(String[] args) throws InterruptedException {

		ExecutorService service = Executors.newFixedThreadPool(1000);
		Counter counter = new VolatileCounter();
		long start = System.currentTimeMillis();
		for (int i = 0; i < 1000; i++) {
			service.submit(new RunnableDemo(counter, 100000));
		}
		service.shutdown();
		service.awaitTermination(1, TimeUnit.HOURS);
		long end = System.currentTimeMillis();
		System.out.println("Count is : " + counter.getCounter());
		System.out.println("The time is : " + (end - start));

	}

	interface Counter {
		void increment();

		long getCounter();
	}

	static class VolatileCounter implements Counter {

		private volatile long count;

		@Override
		public void increment() {
			count++;
		}

		@Override
		public long getCounter() {
			return count;
		}
	}

	static class SyncCounter implements Counter {

		private long count;

		@Override
		public synchronized void increment() {
			count++;
		}

		@Override
		public long getCounter() {
			return count;
		}
	}

	static class LockCounter implements Counter {

		private final Lock lock = new ReentrantLock();
		private long count;

		@Override
		public void increment() {
			try {
				lock.lock();
				count++;
			} finally {
				lock.unlock();
			}
		}

		@Override
		public long getCounter() {
			return count;
		}
	}

	static class AtomicCounter implements Counter {

		private AtomicInteger count = new AtomicInteger();

		@Override
		public void increment() {
			count.incrementAndGet();
		}

		@Override
		public long getCounter() {
			return count.get();
		}
	}

	static class RunnableDemo implements Runnable {

		private final Counter counter;
		private final int num;

		RunnableDemo(Counter counter, int num) {
			this.counter = counter;
			this.num = num;
		}

		@Override
		public void run() {
			for (int i = 0; i < num; i++) {
				counter.increment();
			}
		}
	}


}
