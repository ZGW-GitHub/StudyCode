package aa.thread.e.notify;

/**
 * 此时线程间没有通信，导致线程混乱的随意执行，生产与消费的关系没有建立起来
 *
 * @author NotUpToYou
 */
public class Aa {

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

        Aa aa = new Aa();

        new Thread("p") {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    aa.product();
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
                    aa.consume();
                }
            }
        }.start();

    }

}
