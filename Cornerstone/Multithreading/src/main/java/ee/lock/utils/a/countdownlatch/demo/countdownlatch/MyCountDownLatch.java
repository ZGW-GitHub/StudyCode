package ee.lock.utils.a.countdownlatch.demo.countdownlatch;

public class MyCountDownLatch {

    private final int total;

    private int count = 0;

    public MyCountDownLatch(int total) {
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
            while (count != total) {
                this.wait();
            }
        }
    }

}
