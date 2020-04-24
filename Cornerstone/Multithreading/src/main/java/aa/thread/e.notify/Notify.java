package aa.thread.e.notify;

/**
 * 此时线程间有了通信，生产与消费的关系建立成功
 *
 * 但当多个线程执行生产者、消费者时，会发生严重问题
 *
 * @author NotUpToYou
 */
public class Notify {

    private int i = 0;

    final private Object LOCK = new Object();

    // flag ：true，代表生产了
    private volatile boolean flag = false;

    // 定义生产者
    private void product() {
        synchronized (LOCK) {
            // 如果已经生产了
            if (flag) {
                try {
                    LOCK.wait(); // 放弃锁
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            // 如果还没有生产
            } else {
                // 生产
                System.out.println("生产者 -> " + (++i));
                // 将标识设为生产了
                flag = true;
                // 唤醒消费者
                LOCK.notify();
            }
        }
    }

    // 定义消费者
    private void consume() {
        synchronized (LOCK) {
            // 如果有生产
            if (flag) {
                // 消费
                System.out.println("消费者 -> " + i);
                // 将标识设为没有生产
                flag = false;
                // 唤醒生产者
                LOCK.notify();
            // 如果没有生产
            } else {
                try {
                    LOCK.wait(); // 放弃锁
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {

        Notify demo1 = new Notify();

        // 该线程执行生产者
        new Thread("p") {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    demo1.product();
                }
            }
        }.start();

        // 该线程执行消费者
        new Thread("c") {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    demo1.consume();
                }
            }
        }.start();

    }

}
