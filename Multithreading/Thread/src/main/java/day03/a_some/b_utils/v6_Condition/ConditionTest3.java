package com.a_snow.b_utils.v6_Condition;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

// 只是用公平锁实现 生产者消费者 不行线程执行可能会混乱
public class ConditionTest3 {

    private static final ReentrantLock lock = new ReentrantLock(true);

    private static final Condition PRODUCT_CONDITION = lock.newCondition();

    private static final Condition CONSUMER_CONDITION = lock.newCondition();

    private static final LinkedList<Long> LIST_DATA = new LinkedList<>();

    private static final int MAX_LENGTH = 100;

    public static void main(String[] args) {

        IntStream.range(1, 5).forEach(ConditionTest3::createThread);

    }

    private static void createThread(int num) {
        String i = String.valueOf(num);
        new Thread(() -> {
            while (true) {
                ConditionTest3.consume();
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, i + "消费者").start();

        new Thread(() -> {
            while (true) {
                ConditionTest3.product();
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, i + "生产者").start();

    }

    // 生产者
    private static void product() {

        try {
            lock.lock();
            while (LIST_DATA.size() > MAX_LENGTH) {
                PRODUCT_CONDITION.await();
            }
            long data = System.currentTimeMillis();
            LIST_DATA.addLast(data);
            System.out.println(Thread.currentThread().getName() + " -> 我生产了：" + data);
            CONSUMER_CONDITION.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    // 消费者
    private static void consume() {

        try {
            lock.lock();
            while (LIST_DATA.isEmpty()) {
                CONSUMER_CONDITION.await();
            }
            Long data = LIST_DATA.removeFirst();
            System.out.println(Thread.currentThread().getName() + " -> 我消费了：" + data);
            PRODUCT_CONDITION.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }


}
