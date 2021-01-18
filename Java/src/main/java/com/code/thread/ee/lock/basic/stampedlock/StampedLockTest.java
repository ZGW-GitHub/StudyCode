package com.code.thread.ee.lock.basic.stampedlock;

import org.junit.Test;

import java.util.concurrent.locks.StampedLock;

/**
 * @author 愆凡
 * @date 2020/5/19 4:47 下午
 */
public class StampedLockTest {

	private final StampedLock stampedLock = new StampedLock();

	private final long writeLock = stampedLock.writeLock();
	private final long readLock = stampedLock.readLock();
	private final long optimisticRead = stampedLock.tryOptimisticRead();

	@Test
	public void test() {
	}

}
