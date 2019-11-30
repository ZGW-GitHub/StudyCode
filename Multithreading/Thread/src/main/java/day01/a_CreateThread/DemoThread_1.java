package day01.a_CreateThread;

// 创建线程：方式一
public class DemoThread_1 {

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
