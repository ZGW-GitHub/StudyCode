package e.future.vs.completablefuture.commpletablefuture;

import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class B_FutureVSCompletableFuture {

    public static void main(String[] args) throws InterruptedException {

        // Future 会在 future.get() 处阻塞住，导致要等到所有线程获取完数据，才能再向下进行
//        testFuture();
        // CompletableFuture 会使得先完成获取数据的线程先向下进行
        testCompletableFuture();


    }

    private static void testFuture() throws InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        List<Callable<Integer>> callableList = IntStream.range(0, 5).
                boxed().
                map(integer -> (Callable<Integer>) B_FutureVSCompletableFuture::get)
                .collect(Collectors.toList());

        executorService.invokeAll(callableList).stream().map(future -> {
            try {
                return future.get();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        }).parallel().forEach(B_FutureVSCompletableFuture::display);

    }

    private static void testCompletableFuture() throws InterruptedException {

        IntStream.range(0, 10).boxed().
                forEach(integer -> CompletableFuture.supplyAsync(B_FutureVSCompletableFuture::get)
                .thenAccept(B_FutureVSCompletableFuture::display)
                .whenComplete((aVoid, throwable) -> System.out.println(integer + "===============")));

        Thread.currentThread().join();

    }

    // 模拟从数据库获取数据
    public static Integer get() {

        int time = new Random(System.currentTimeMillis()).nextInt(10);
        System.out.println(Thread.currentThread().getName() + " ---> 获取数据中！" + time);
        try {
            TimeUnit.SECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " ---> 获取数据完成！" + time);
        return time;
    }

    // 模拟使用获取到的数据进行工作
    private static void display(int data) {

        System.out.println(Thread.currentThread().getName() + " ---> 工作中！" + data);
        try {
            TimeUnit.SECONDS.sleep(data);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " ---> 工作结束！" + data);

    }

}
