package learn5_FutureVSCompletableFuture.c_completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class D_Api_Complete {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("执行中。。。");
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "OVER";
        });

        // 在这里不等待的话，任务因为还没起来，就调用了 complete 方法，导致，任务被取消
//        TimeUnit.SECONDS.sleep(1);
        boolean complete = future.complete("没完成呐！");
        System.out.println(complete);
        System.out.println(future.get());
        System.out.println("================");

    }

}
