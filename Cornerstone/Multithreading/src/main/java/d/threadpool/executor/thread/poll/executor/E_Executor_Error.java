package d.threadpool.executor.thread.poll.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * 通过自定义 Runnable 实现异常的处理
 */
public class E_Executor_Error {

    public static void main(String[] args) throws InterruptedException {

        runnableTask();

    }

    private static void runnableTask() throws InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        IntStream.range(0, 10).boxed().forEach(integer -> executorService.execute(
                new MyTaskRunnable(integer) {
                    @Override
                    protected void doInit() {
                        System.out.println(integer + " -> Init！");
                    }

                    @Override
                    protected void done() {
                        System.out.println(integer + " -> Done！");
                    }

                    @Override
                    protected void doExecute() {
                        if (integer % 3 == 0) {
                            int a = integer/0;
                        }
                    }

                    @Override
                    protected void error(Throwable cause) {
                        System.out.println(integer + " -> error！");
                        cause.printStackTrace();
                    }
                }
        ));

        executorService.shutdown();
        executorService.awaitTermination(10, TimeUnit.SECONDS);
        System.out.println("结束。。。");

    }

    private static abstract class MyTaskRunnable implements Runnable {

        final int threadName;

        MyTaskRunnable(int threadName) {
            this.threadName = threadName;
        }

        @Override
        public void run() {
            try {
                this.doInit();
                this.doExecute();
                this.done();
            } catch (Throwable cause) {
                this.error(cause);
            }
        }

        protected abstract void doExecute();

        protected abstract void done();

        protected abstract void doInit();

        protected abstract void error(Throwable cause);

    }

}
