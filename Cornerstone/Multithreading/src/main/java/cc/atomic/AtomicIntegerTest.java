package cc.atomic;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author space
 */
public class AtomicIntegerTest {

    private volatile static int value = 0;
    private static AtomicInteger value2 = new AtomicInteger();

    private static Set<Integer> set = Collections.synchronizedSet(new HashSet<>());
    private static Set<Integer> set2 = Collections.synchronizedSet(new HashSet<>());

    public static void main(String[] args) throws InterruptedException {


        Thread t1 = new Thread(()->{
            for (int i = 0; i < 1_000; i++) {
                set.add(value);
                set2.add(value2.getAndIncrement());
                value = value + 1;
                System.out.println(Thread.currentThread().getName() + " --> " + value);
            }
        });
        Thread t2 = new Thread(()->{
            for (int i = 0; i < 1_000; i++) {
                set.add(value);
                set2.add(value2.getAndIncrement());
                value = value + 1;
                System.out.println(Thread.currentThread().getName() + " --> " + value);
            }
        });
        Thread t3 = new Thread(()->{
            for (int i = 0; i < 1_000; i++) {
                set.add(value);
                set2.add(value2.getAndIncrement());
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


        System.out.println(set.size());
        System.out.println(set2.size());

    }

}
