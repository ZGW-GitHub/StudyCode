package nn.threadpool.executors.threadpool.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * return new ThreadPoolExecutor(nThreads,
 *                               nThreads,
 *                               0L, TimeUnit.MILLISECONDS,
 *                               new LinkedBlockingQueue<Runnable>());
 *
 *        nThreads ：核心线程数 与 最大线程数相同，都为传入的参数。
 *        程序不会自动关闭
 */
public class FixedThreadPool {

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = getFixedThreadPool();

        System.out.println(((ThreadPoolExecutor)executorService).getActiveCount()); // 0

        IntStream.range(0, 100).boxed().forEach(integer -> executorService.execute(()->{
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println(Thread.currentThread().getName() + " is ok !");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }));
        TimeUnit.SECONDS.sleep(1);

        System.out.println(((ThreadPoolExecutor)executorService).getActiveCount()); // 10

    }

    /**
     * return new ThreadPoolExecutor(nThreads, nThreads,
     *                                       0L, TimeUnit.MILLISECONDS,
     *                                       new LinkedBlockingQueue<Runnable>());
     */
    private static ExecutorService getFixedThreadPool() {

        return Executors.newFixedThreadPool(10);

    }

}
