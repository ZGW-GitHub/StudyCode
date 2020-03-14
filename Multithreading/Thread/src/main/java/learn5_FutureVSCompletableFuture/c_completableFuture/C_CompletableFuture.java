package learn5_FutureVSCompletableFuture.c_completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * API :
 *
 *      thenAccept --------
 *                        |------> 带 Async 是异步工作的，不带就是同步的
 *      thenAcceptAsync ---
 *
 *      runAfterBoth ：等待前后两个 CompletableFuture 完成后再执行 第二个参数传入的 Runnable
 */
public class C_CompletableFuture {

    public static void main(String[] args) throws InterruptedException {

        // thenAccept 是同步的
        test();

    }

    private static void test() throws InterruptedException {

        CompletableFuture
                .supplyAsync(Object::new)
                .thenAcceptAsync(o -> {
                    try {
                        System.out.println("=== 第一阶段，开始：===");
                        TimeUnit.SECONDS.sleep(10);
                        System.out.println("=== 第一阶段，结束：===");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                })
                .runAfterBoth(
                        CompletableFuture
                                .supplyAsync(() -> "Hello")
                                .thenAcceptAsync(s -> {
                                    try {
                                        System.out.println("=== 第二阶段，开始：===");
                                        TimeUnit.SECONDS.sleep(5);
                                        System.out.println("=== 第二阶段，结束：===");
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }),
                        () -> System.out.println("=== All Finished ===")
                );

        Thread.currentThread().join();

    }

}
