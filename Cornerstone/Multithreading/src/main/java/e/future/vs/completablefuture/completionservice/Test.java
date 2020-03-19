package e.future.vs.completablefuture.completionservice;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Test {

    private static final ExecutorService executorService = Executors.newFixedThreadPool(5);

    private static final CompletionService<Object> completionService = new ExecutorCompletionService<>(executorService);

    public static void main(String[] args) throws ExecutionException, InterruptedException {

//        testBusy();
        testByCompletionService();

    }

    private static void testBusy() throws ExecutionException, InterruptedException {

        List<Future<?>> futureList = new ArrayList<>();

        List<Runnable> runnableList = IntStream.range(0, 5).boxed().map(integer -> (Runnable) () -> {
            System.out.println(integer + " 执行了！");
            try {
                TimeUnit.SECONDS.sleep(integer * 5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).collect(Collectors.toList());

        runnableList.forEach((runnable)->{
            Future<?> future = executorService.submit(runnable);
            futureList.add(future);
        });

        // get(4) 因为 4 执行时间最长，所以会导致 get(1) 等，阻塞
        // 反观使用 CompletionService 就不会。
        futureList.get(4).get();
        System.out.println("====");
        futureList.get(1).get();
        System.out.println("====");
        futureList.get(2).get();
        System.out.println("====");
        futureList.get(3).get();
        System.out.println("====");
        futureList.get(1).get();
        System.out.println("====");

    }

    private static void testByCompletionService() throws InterruptedException, ExecutionException {

        List<Runnable> runnableList = IntStream.range(0, 5).boxed().map(integer -> (Runnable) () -> {
            System.out.println(integer + " 执行了！");
            try {
                TimeUnit.SECONDS.sleep(integer * 6);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).collect(Collectors.toList());

        runnableList.forEach((runnable) -> completionService.submit(Executors.callable(runnable)));

        Future<?> f;
        while ((f = completionService.take()) != null) {
            System.out.println(f.get());
            System.out.println("===");
        }

    }

}
