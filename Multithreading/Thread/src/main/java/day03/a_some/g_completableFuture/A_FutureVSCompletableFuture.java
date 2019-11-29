package com.a_snow.g_completableFuture;

/* ******************************
 *   Time:  2019-07-26 6:39     *
 ****************************** */

import java.util.concurrent.*;

public class A_FutureVSCompletableFuture {

    public static void main(String[] args) throws InterruptedException {

//        testBusy();
        testCompletableFuture();

    }

    private static void testBusy() throws ExecutionException, InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        Future<?> future = executorService.submit(() -> {
            try {
                System.out.println("运行了！");
                TimeUnit.SECONDS.sleep(5);
                System.out.println("结束！");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        while (!future.isDone()) {
            System.out.println(future.get());
        }

    }

    private static void testCompletableFuture() throws InterruptedException {

        CompletableFuture.runAsync(() -> {
            try {
                System.out.println("运行了！");
                TimeUnit.SECONDS.sleep(5);
                System.out.println("结束！");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).whenComplete(((aVoid, throwable) -> System.out.println("后续操作！")));

        System.out.println("是否被 block 住？ 不是！");

        // 因为 CompletableFuture 提交的任务，执行线程默认为守护线程。
        Thread.currentThread().join();

    }

}
