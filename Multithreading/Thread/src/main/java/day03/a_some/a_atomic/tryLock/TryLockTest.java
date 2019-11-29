package com.a_snow.a_atomic.tryLock;

public class TryLockTest {

    public static void main(String[] args) {

        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                try {
                    doSomething();
                } catch (TryLockException | InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }

    }

    private static void doSomething() throws TryLockException, InterruptedException {
        try {
            TryLock.tryLock();
            System.out.println("get the Lock !");
            Thread.sleep(10_000);
        } finally {
            TryLock.unLock();
        }
    }

}
