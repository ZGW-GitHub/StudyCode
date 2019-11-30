package learn1_Thread.e_Thread_notify;

/**
 * 但当多个线程执行生产者、消费者时，会发生严重问题，问题是：
 *
 * 发生程序莫名停止
 *
 * 产生该问题的原因是：notify 对唤醒哪个线程的选择是复杂的，导致了代码中四个线程都 wait 了。
 */
public class C_MoreThread_notify {

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
                System.out.println("生产者" + Thread.currentThread().getName() + " -> " + (++i));
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
                System.out.println("消费者" + Thread.currentThread().getName() + " -> " + i);
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

        C_MoreThread_notify demo1 = new C_MoreThread_notify();

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

        // 该线程执行生产者
        new Thread("p2") {
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

        // 该线程执行消费者
        new Thread("c2") {
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
