package nn.threadpool.old.future.vs.completablefuture.commpletablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * exceptionally
 *      如果产生异常就执行该语句，否则跳过该语句。
 */
public class E_Api_Exceptionally {

    public static void main(String[] args) throws InterruptedException {

        CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> "AAA" + 2/0)
                .exceptionally((T)-> {
                    System.out.println("错误了！");
                    return T.getMessage();
                })
                .thenAccept(System.out::println);

        TimeUnit.SECONDS.sleep(2);

        // 因为 CompletableFuture<Void> future 为 Void ，所以 aVoid = null
        future.whenComplete(((aVoid, throwable) -> System.out.println(aVoid)));

        Thread.currentThread().join();

    }

}
