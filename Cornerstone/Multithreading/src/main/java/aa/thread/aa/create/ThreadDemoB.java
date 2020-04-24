package aa.thread.aa.create;

/**
 * 通过创建类实现 Runnable 接口，来创建线程
 *
 * @author NotUpToYou
 * @date 2019-11-30 15:35
 */
public class ThreadDemoB {

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
