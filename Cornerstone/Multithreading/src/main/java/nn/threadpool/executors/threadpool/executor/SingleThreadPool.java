package nn.threadpool.executors.threadpool.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * SingleThreadPool 与 自己创建一个 Thread 的区别：
 *      Thread 当任务完成后，它就死了。而，SingleThreadPool 会一直存活。
 *      Thread 不能提交 Runnable 到一个队列中去等待，而 SingleThreadPool 能。
 */
public class SingleThreadPool {

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = getSingleThreadExecutor();

        // 不能输出了，因为获取 SingleThreadExecutor 时被包装了，不能强转为 ThreadPoolExecutor
//        System.out.println(((ThreadPoolExecutor)executorService).getActiveCount()); // 0

        IntStream.range(0, 100).boxed().forEach(integer -> executorService.execute(()->{
            try {
                TimeUnit.SECONDS.sleep(2);
                System.out.println(Thread.currentThread().getName() + " is ok !");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }));
        TimeUnit.SECONDS.sleep(1);

        // 不能输出了，因为获取 SingleThreadExecutor 时被包装了，不能强转为 ThreadPoolExecutor
//        System.out.println(((ThreadPoolExecutor)executorService).getActiveCount()); // 1

    }

    /**
     * return new FinalizableDelegatedExecutorService(  new ThreadPoolExecutor(
     *                                                      1, 1,
     *                                                      0L,
     *                                                      TimeUnit.MILLISECONDS,
     *                                                      new LinkedBlockingQueue<Runnable>()));
     *  获取 SingleThreadExecutor 时 ThreadPoolExecutor 被 FinalizableDelegatedExecutorService 封装了，
     *  所以，SingleThreadExecutor 不能强转为 ThreadPoolExecutor
     */
    private static ExecutorService getSingleThreadExecutor() {

        return Executors.newSingleThreadExecutor();

    }

}
