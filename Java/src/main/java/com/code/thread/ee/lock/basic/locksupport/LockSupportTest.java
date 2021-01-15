package com.code.thread.ee.lock.basic.locksupport;

import org.junit.Test;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.LockSupport;

/**
 * @author 愆凡
 * @date 2020/5/11 9:14 上午
 */
public class LockSupportTest {

	static {
		// Reduce the risk of "lost unpark" due to classloading
		Class<?> ensureLoaded = LockSupport.class;
	}

	private final AtomicBoolean locked = new AtomicBoolean(false);
	private final Queue<Thread> waiters = new ConcurrentLinkedQueue<>();

	public void lock() {
		boolean wasInterrupted = false;
		// publish current thread for unparkers
		waiters.add(Thread.currentThread());

		// Block while not first in queue or cannot acquire lock
		while (waiters.peek() != Thread.currentThread() ||
				!locked.compareAndSet(false, true)) {
			LockSupport.park(this);
			// ignore interrupts while waiting
			if (Thread.interrupted()) {
				wasInterrupted = true;
			}
		}

		waiters.remove();
		// ensure correct interrupt status on return
		if (wasInterrupted) {
			Thread.currentThread().interrupt();
		}
	}

	public void unlock() {
		locked.set(false);
		LockSupport.unpark(waiters.peek());
	}

	@Test
	public void test() {
//		LockSupport
	}

}
