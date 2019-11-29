package com.a_snow.d_executors;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/********************************
 *   Time:  2019-07-24 8:48    *
 ********************************/

// WorkStealingPool
// 这次使用了 Callable ，而没有使用 Runnable
public class D_WorkStealingPool {

    /**
     * return new ForkJoinPool(
     *              Runtime.getRuntime().availableProcessors(), // 计算机核数
     *              ForkJoinPool.defaultForkJoinWorkerThreadFactory,
     *              null,
     *              true);
     */
    private static final ExecutorService executorService = Executors.newWorkStealingPool();

    public static void main(String[] args) throws InterruptedException {

        List<Callable<String>> callableList = IntStream.range(0, 16).boxed().map(integer -> (Callable<String>) () -> {
            System.out.println(Thread.currentThread().getName());
            TimeUnit.SECONDS.sleep(2);
            return "Task - " + integer;
        }).collect(Collectors.toList());

        executorService.invokeAll(callableList).stream().map((future)->{
            try {
                return future.get();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).forEach(System.out::println);

    }

}
