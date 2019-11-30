/*
      Date:  2019-11-30 15:35
                                 */
package learn1_Thread.a_CreateThread;

// 创建线程：方式二
public class DemoThread_2 {

    public static void main(String[] args) {

        new Thread(new MyRunnable()).start();

    }

    private static class MyRunnable implements Runnable {
        @Override
        public void run() {
            // 线程需要执行的业务逻辑
        }
    }

}