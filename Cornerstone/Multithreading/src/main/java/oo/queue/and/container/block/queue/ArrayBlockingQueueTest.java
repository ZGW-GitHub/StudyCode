package oo.queue.and.container.block.queue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 注意：
 *      大小固定、不允许NULL
 */
public class ArrayBlockingQueueTest {

    private static final ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(2);

    public static void main(String[] args) throws InterruptedException {

//        testAdd();
//        testOffer();
//        testPut();
//        testPoll();
//        testPeek();
//        testElement();
//        testRemove();
//        testTake();
//        testDrainTo();
//        testRemainingCapacity();

    }

    // 满，抛异常
    private static void testAdd() {

        queue.add(1);
        queue.add(2);
//        System.out.println(queue.add(3)); // 抛异常
    }

    // 满，返回 false
    private static void testOffer() {

        queue.offer(1);
        queue.offer(2);
        System.out.println(queue.offer(3)); // 返回 false
    }

    // 满，阻塞
    private static void testPut() throws InterruptedException {

        queue.put(1);
        queue.put(2);

        new Thread(() -> queue.remove(1)).start();
        TimeUnit.SECONDS.sleep(1);

        queue.put(3); // 阻塞住

        new Thread(() -> queue.remove(2)).start();
        TimeUnit.SECONDS.sleep(1);

        queue.put(4); // 排后面

        System.out.println("结束！");

    }

    // 删除并返回头。
    // 空：返回null
    private static void testPoll() {

        queue.add(1);
        queue.add(2);
        System.out.println(queue.poll()); // 1
        System.out.println(queue.poll()); // 2
        System.out.println(queue.poll()); // null

    }

    // 返回头
    // 空：返回null
    private static void testPeek() {

        queue.add(1);
        queue.add(2);
        System.out.println(queue.peek()); // 1
        System.out.println(queue.peek()); // 1

        queue.clear();
        System.out.println(queue.peek()); // null

    }

    // 返回头
    // 空：抛异常
    private static void testElement() {

        queue.add(1);
        System.out.println(queue.element()); // 1
        System.out.println(queue.element()); // 1

        queue.clear();
        System.out.println(queue.element()); // 抛异常

    }

    // 删除并返回头。
    // 空：抛异常
    private static void testRemove() {

        queue.add(1);
        System.out.println(queue.remove()); // 1
        System.out.println(queue.remove()); // 抛异常

    }

    // 删除并返回头
    // 空：阻塞
    private static void testTake() throws InterruptedException {
        queue.add(1);
        System.out.println(queue.take());
        System.out.println(queue.take()); // 阻塞住
    }

    // 将队列中的值复制到一个容器
    private static void testDrainTo() {

        queue.add(1);
        queue.add(2);

        List<Integer> list = new ArrayList<>();
        queue.drainTo(list);

        System.out.println(list.toString());

    }

    // 剩余容量
    private static void testRemainingCapacity() {

        System.out.println(queue.remainingCapacity()); // 2
        queue.add(1);
        System.out.println(queue.remainingCapacity()); // 1
        queue.add(2);
        System.out.println(queue.remainingCapacity()); // 0

    }

}
