package d.threadpool.executor.thread.poll.executor;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * invokeAll(callableList) ：
 *      提交了多个 Callable ，并等待其全部完成。
 */
public class G_Executor_Api_invokeAll {

    public static void main(String[] args) throws InterruptedException {

        testInvokeAll();

    }

    private static void testInvokeAll() throws InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(20);

        List<Callable<Integer>> callableList = IntStream.range(0, 5).boxed().map(integer -> (Callable<Integer>) () -> {
            TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(10));
            System.out.println(Thread.currentThread().getName() + " ---> 工作了！ ");
            return integer;
        }).collect(Collectors.toList());

        List<Future<Integer>> invokeAll = executorService.invokeAll(callableList);

        System.out.println("---------------------> finish !");

        invokeAll.stream().map(integerFuture -> {
            try {
                return integerFuture.get();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        }).forEach(System.out::println);

    }

}
