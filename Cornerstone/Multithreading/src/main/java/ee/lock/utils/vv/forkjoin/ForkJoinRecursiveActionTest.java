package ee.lock.utils.vv.forkjoin;

import java.util.Optional;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class ForkJoinRecursiveActionTest {

    private final static ForkJoinPool FORK_JOIN_POOL = new ForkJoinPool();

    private final static RecursiveActionTest action = new RecursiveActionTest(0, 50);

    private final static int MAX = 6;

    private final static AtomicInteger NUM = new AtomicInteger();

    public static void main(String[] args) throws InterruptedException {

        FORK_JOIN_POOL.submit(action);

        FORK_JOIN_POOL.awaitTermination(1, TimeUnit.SECONDS);

        Optional.of(NUM).ifPresent(System.out::println);

    }

    private static class RecursiveActionTest extends RecursiveAction{

        private final int start;
        private final int end;

        RecursiveActionTest(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected void compute() {
            if (end - start <= 6) {
                NUM.addAndGet(IntStream.rangeClosed(start, end).sum());
            } else {
                int middle = (start + end) / 2;
                RecursiveActionTest left = new RecursiveActionTest(start, middle);
                RecursiveActionTest right = new RecursiveActionTest(middle + 1, end);
                left.fork();
                right.fork();
            }
        }

    }

}
