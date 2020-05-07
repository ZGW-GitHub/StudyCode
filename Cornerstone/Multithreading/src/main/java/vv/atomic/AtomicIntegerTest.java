package vv.atomic;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author space
 */
public class AtomicIntegerTest {

    private volatile static int value = 0;
    private static final AtomicInteger VALUE2 = new AtomicInteger();

    private static final Set<Integer> SET = Collections.synchronizedSet(new HashSet<>());
    private static final Set<Integer> SET2 = Collections.synchronizedSet(new HashSet<>());

    public static void main(String[] args) throws InterruptedException {

		@SuppressWarnings("all")
        Thread t1 = new Thread(()->{
            for (int i = 0; i < 1_000; i++) {
                SET.add(value);
                SET2.add(VALUE2.getAndIncrement());
                value = value + 1;
                System.out.println(Thread.currentThread().getName() + " --> " + value);
            }
        });

		@SuppressWarnings("all")
        Thread t2 = new Thread(()->{
            for (int i = 0; i < 1_000; i++) {
                SET.add(value);
                SET2.add(VALUE2.getAndIncrement());
                value = value + 1;
                System.out.println(Thread.currentThread().getName() + " --> " + value);
            }
        });

		@SuppressWarnings("all")
        Thread t3 = new Thread(()->{
            for (int i = 0; i < 1_000; i++) {
                SET.add(value);
                SET2.add(VALUE2.getAndIncrement());
                value = value + 1;
                System.out.println(Thread.currentThread().getName() + " --> " + value);
            }
        });

        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();


        System.out.println(SET.size());
        System.out.println(SET2.size());

    }

}
