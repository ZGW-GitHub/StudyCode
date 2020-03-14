package learn4_JUC.d_executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * ThreadPoolExecutor(  0,
 *                      Integer.MAX_VALUE,
 *                      60L,
 *                      TimeUnit.SECONDS,
 *                      new SynchronousQueue<Runnable>());
 *
 *     SynchronousQueue<Runnable> : 大小为1，进来一个前一个就出去，一直进一直出。
 *
 *     当活动线程为0，且keepAliveTime时间到达，则该线程会被销毁，直到线程数为0，程序自动关闭。
 */
public class A_CachedThreadPool {

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = getCachedThreadPool();

        System.out.println(((ThreadPoolExecutor)executorService).getActiveCount()); // 0

        IntStream.range(0, 100).boxed().forEach(integer -> executorService.execute(()->{
            try {
                TimeUnit.SECONDS.sleep(6);
                System.out.println(Thread.currentThread().getName() + " is ok !");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }));
        TimeUnit.SECONDS.sleep(1);

        System.out.println(((ThreadPoolExecutor)executorService).getActiveCount()); // 100

    }

    /**
     * return new ThreadPoolExecutor(0,
     *                               Integer.MAX_VALUE,
     *                               60L, TimeUnit.SECONDS,
     *                               new SynchronousQueue<Runnable>());
     */
    private static ExecutorService getCachedThreadPool() {

        return Executors.newCachedThreadPool();

    }

}
