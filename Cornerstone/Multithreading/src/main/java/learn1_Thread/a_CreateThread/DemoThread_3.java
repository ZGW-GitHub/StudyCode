/*
      Date:  2019-11-30 15:49
                                 */
package learn1_Thread.a_CreateThread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

// 创建线程：方式三
public class DemoThread_3 {

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
