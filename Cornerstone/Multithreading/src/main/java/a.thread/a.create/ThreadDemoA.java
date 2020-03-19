package a.thread.a.create;

/**
 * 创建线程：方式一
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
