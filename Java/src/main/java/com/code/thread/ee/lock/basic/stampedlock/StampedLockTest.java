package com.code.thread.ee.lock.basic.stampedlock;

import java.util.concurrent.locks.StampedLock;

/**
 * @author 愆凡
 * @date 2020/5/19 4:47 下午
 */
public class StampedLockTest {

	private static final StampedLock STAMPEDLOCK = new StampedLock();

	private static final long WRITELOCK = STAMPEDLOCK.writeLock();
	private static final long READLOCK = STAMPEDLOCK.readLock();
	private static final long OPTIMISTICREADLOCK = STAMPEDLOCK.tryOptimisticRead();

	public static void main(String[] args) {


	}
}
