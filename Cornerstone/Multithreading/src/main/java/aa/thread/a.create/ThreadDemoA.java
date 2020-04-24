package aa.thread.a.create;

/**
 * 通过创建类继承 Thread 类来创建线程
 *
 * @author 愆凡
 */
public class ThreadDemoA {

    public static void main(String[] args) throws InterruptedException {

        new MyThread().start();

    }

    private static class MyThread extends Thread {
        @Override
        public void run() {
            // 线程需要执行的业务逻辑
        }
    }

}
