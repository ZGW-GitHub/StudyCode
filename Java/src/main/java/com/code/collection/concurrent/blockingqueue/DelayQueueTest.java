package com.code.collection.concurrent.blockingqueue;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 无界
 *
 * @author 愆凡
 */
public class DelayQueueTest {

	private final DelayQueue<Test> queue = new DelayQueue<>();

	class Test implements Delayed {
		@Override
		public long getDelay(TimeUnit timeUnit) {
			System.out.println(timeUnit.toString());
			return 0;
		}

		@Override
		public int compareTo(Delayed delayed) {
			System.out.println(delayed.toString());
			return 0;
		}
	}

}
