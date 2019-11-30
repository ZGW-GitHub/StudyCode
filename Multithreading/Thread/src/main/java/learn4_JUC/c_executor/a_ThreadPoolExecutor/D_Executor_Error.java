package learn4_JUC.c_executor.a_ThreadPoolExecutor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * 定义异常处理器，并为 ExecutorService 设置。
 */
public class D_Executor_Error {

    public static void main(String[] args) throws InterruptedException {

        runnableError();

    }

    private static void runnableError() throws InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(10, new MyThreadFactory());

        executorService.execute(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        IntStream.range(0,10).boxed().forEach(integer -> executorService.execute(()-> System.out.println(1/0)));

        executorService.shutdown();
        executorService.awaitTermination(10, TimeUnit.SECONDS);
        System.out.println("结束。。。");

    }

    private static class MyThreadFactory implements ThreadFactory {

        private static final AtomicInteger SEQ = new AtomicInteger();

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            thread.setName("MyThread- " + SEQ.getAndIncrement());
            // 设置异常处理程序
            thread.setUncaughtExceptionHandler((athread, cause) -> {
                System.out.println("The Thread : " + athread.getName() + " 执行失败！");
                // 输出堆栈信息
                cause.printStackTrace();
                System.out.println("------------------------------------------");
            });
            return thread;
        }
    }

}
