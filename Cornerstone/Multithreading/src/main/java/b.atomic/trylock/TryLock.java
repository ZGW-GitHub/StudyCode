package b.atomic.trylock;

import java.util.concurrent.atomic.AtomicInteger;

public class TryLock {

    private static AtomicInteger integer = new AtomicInteger(0);

    private static Thread thread = new Thread();

    static void tryLock() throws TryLockException, InterruptedException {
        boolean flag = integer.compareAndSet(0, 1);
        if (!flag) {
            throw new TryLockException();
        }
        thread = Thread.currentThread();
    }

    static void unLock() {
        if (integer.get() == 0) {
            return;
        }
        if (thread == Thread.currentThread()) {
            integer.compareAndSet(1, 0);
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