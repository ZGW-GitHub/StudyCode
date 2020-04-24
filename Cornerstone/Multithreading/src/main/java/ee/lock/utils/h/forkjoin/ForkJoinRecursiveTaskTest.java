package ee.lock.utils.h.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.IntStream;

public class ForkJoinRecursiveTaskTest {

    private static final RecursiveTaskTest recursiveTask = new RecursiveTaskTest(0, 20);

    private static final ForkJoinPool forkJoinPool = new ForkJoinPool();

    private static final int MAX = 6;

    public static void main(String[] args) {

        // 使用 ForkJoinPool 操作 RecusiveTask
        ForkJoinTask<Integer> task = forkJoinPool.submit(recursiveTask);

        try {
            // 获取值
            Integer result = task.get();

            System.out.println(result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

    }

    private static class RecursiveTaskTest extends RecursiveTask<Integer> {

        private final int start;

        private final int end;

        RecursiveTaskTest(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected Integer compute() {

            if (end - start <= MAX) {
                return IntStream.rangeClosed(start, end).sum();
            } else {
                int middle = (end + start)/2;
                RecursiveTaskTest left = new RecursiveTaskTest(start, middle);
                RecursiveTaskTest right = new RecursiveTaskTest(middle + 1, end);

                // 以异步方式执行任务
                left.fork();
                right.fork();

                // 获取计算结果
                Integer result = left.join() + right.join();

                return result;

            }

        }
    }

}
