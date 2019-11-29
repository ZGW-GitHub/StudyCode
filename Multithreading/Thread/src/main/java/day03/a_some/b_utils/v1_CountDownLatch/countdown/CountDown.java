package com.a_snow.b_utils.v1_CountDownLatch.countdown;

public class CountDown {

    private final int total;

    private int count = 0;

    public CountDown(int total) {
        this.total = total;
    }

    public void down() {
        synchronized (this) {
            this.count++;
            this.notifyAll();
        }
    }

    public void await() throws InterruptedException {
        synchronized (this) {
            while (count != total)
                this.wait();
        }
    }

}
