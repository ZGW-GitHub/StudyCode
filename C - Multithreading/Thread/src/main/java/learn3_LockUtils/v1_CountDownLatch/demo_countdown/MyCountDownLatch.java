package learn3_LockUtils.v1_CountDownLatch.demo_countdown;

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
