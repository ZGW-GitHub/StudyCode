package com.a_snow.b_utils.v5_Lock.ReadWriteLock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

// 读写锁（可重入）
//      和重入锁的 API 类似
public class ReadWriteLockTest {

    private static final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    private static final Lock readLock = lock.readLock();

    private static final Lock writeLock = lock.writeLock();

    private static int num = 0;

    public static void main(String[] args) {


    }

    public static void read() {
        readLock.lock();
    }

}
