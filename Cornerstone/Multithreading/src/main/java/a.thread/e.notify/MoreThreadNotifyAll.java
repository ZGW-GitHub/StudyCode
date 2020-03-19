package a.thread.e.notify;

import java.util.stream.Stream;

/**
 * 对多线程执行 生产者、消费者，产生的问题的解决
 *
 * @author NotUpToYou
 */
public class MoreThreadNotifyAll {

    private int i = 0;

    final private Object LOCK = new Object();

    // flag ：true，代表生产了
    private volatile boolean flag = false;

    // 定义生产者
    private void product() {
        synchronized (LOCK) {
            while (flag) {
                try {
                    LOCK.wait(); // 放弃锁
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // 生产
            System.out.println("生产者" + Thread.currentThread().getName() + " -> " + (++i));
            // 将标识设为生产了
            flag = true;
            // 唤醒消费者
            LOCK.notifyAll();
        }
    }

    // 定义消费者
    private void consume() {
        synchronized (LOCK) {
            while (!flag) {
                try {
                    LOCK.wait(); // 放弃锁
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // 消费
            System.out.println("消费者" + Thread.currentThread().getName() + " -> " + i);
            // 将标识设为没有生产
            flag = false;
            // 唤醒生产者
            LOCK.notifyAll();
        }
    }

    public static void main(String[] args) {

        MoreThreadNotifyAll demo1 = new MoreThreadNotifyAll();

        // 执行生产者的线程
        Stream.of("P1", "P2").forEach(n ->
                new Thread(n) {
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
                }.start()
        );

        // 执行消费者的线程
        Stream.of("C1", "C2").forEach(n ->
                new Thread(n) {
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
                }.start()
        );
    }

}
