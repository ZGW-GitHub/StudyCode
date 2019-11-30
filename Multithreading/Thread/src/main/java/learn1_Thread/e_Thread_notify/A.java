package learn1_Thread.e_Thread_notify;

/**
 * 此时线程间没有通信，导致线程混乱的随意执行，生产与消费的关系没有建立起来
 */
public class A {

    private int i = 0;

    final private Object LOCK = new Object();

    private void product() {
        synchronized (LOCK) {
            System.out.println("生产者 -> " + (++i));
        }
    }

    private void consume() {
        synchronized (LOCK) {
            System.out.println("消费者 -> " + i);
        }
    }

    public static void main(String[] args) {

        A a = new A();

        new Thread("p") {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    a.product();
                }
            }
        }.start();

        new Thread("c") {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    a.consume();
                }
            }
        }.start();

    }

}
