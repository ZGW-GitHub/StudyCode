package nn.threadpool.executors.threadpool.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author NotUpToYou
 */
public class CachedThreadPoolDemoA {

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = Executors.newCachedThreadPool();

        // 0
        System.out.println(((ThreadPoolExecutor) executorService).getActiveCount());

        IntStream.range(0, 100).boxed().forEach(i -> executorService.execute(() -> {
            try {
                TimeUnit.SECONDS.sleep(6);
                System.out.println(Thread.currentThread().getName() + " is ok !");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }));
        TimeUnit.SECONDS.sleep(1);

		// 100
        System.out.println(((ThreadPoolExecutor) executorService).getActiveCount());

    }

}
