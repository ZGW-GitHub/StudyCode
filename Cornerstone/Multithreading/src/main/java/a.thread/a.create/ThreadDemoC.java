package a.thread.a.create;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author NotUpToYou
 *
 * 创建线程：方式三
 */
public class ThreadDemoC {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        FutureTask<String> futureTask = new FutureTask<>(new MyCallable());
        // 实际还是以 Callable 来创建并启动线程
        new Thread(futureTask).start();
        futureTask.get();

    }

    private static class MyCallable implements Callable<String> {
        @Override
        public String call() throws Exception {
            // 线程需要执行的业务逻辑
            return null;
        }
    }

}
