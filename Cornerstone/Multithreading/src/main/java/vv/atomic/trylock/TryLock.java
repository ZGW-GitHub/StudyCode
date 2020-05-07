package vv.atomic.trylock;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author 愆凡
 */
public class TryLock {

    private static final AtomicInteger INTEGER = new AtomicInteger(0);

    private static Thread thread;

    static void tryLock() throws TryLockException, InterruptedException {
        boolean flag = INTEGER.compareAndSet(0, 1);
        if (!flag) {
            throw new TryLockException();
        }
        thread = Thread.currentThread();
    }

    static void unLock() {
        if (INTEGER.get() == 0) {
            return;
        }
        if (thread == Thread.currentThread()) {
            INTEGER.compareAndSet(1, 0);
        }
    }

    private static class TryLockException extends Exception {

        public TryLockException() {
            super();
        }

        public TryLockException(String message) {
            super(message);
        }

    }

}
