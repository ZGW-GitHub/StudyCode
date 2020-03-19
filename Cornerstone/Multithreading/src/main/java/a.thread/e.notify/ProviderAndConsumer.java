package a.thread.e.notify;

import java.util.*;

/**
 * @author NotUpToYou
 */
public class ProviderAndConsumer {

    // 存放工作中的线程
    final static private LinkedList<Control> CONTROLS = new LinkedList();

    private final static int MAX_WORKER = 3;

    public static void main(String[] args) {

        List<Thread> worker = new ArrayList<>();
        Arrays.asList("M1", "M2", "M3", "M4", "M5", "M6", "M7", "M8", "M9", "M10")
                .stream()
                .map(ProviderAndConsumer::createThread)
                .forEach((t) -> {
                    t.start();
                    worker.add(t);
                });

        worker.stream().forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Optional.of("工作结束！").ifPresent(System.out::println);

    }

    private static Thread createThread(String name) {
        return new Thread(() -> {
            Optional.of(Thread.currentThread().getName() + " 已启动！").ifPresent(System.out::println);
            synchronized (CONTROLS){
                // 判断工作线程“队列”是否大于 5
                while (CONTROLS.size() >= MAX_WORKER){
                    try {
                        // 大于 5 ，该线程等待，并放弃锁。
                        CONTROLS.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                // 工作线程“队列”不大于 5
                CONTROLS.addLast(new Control());
            }

            Optional.of(Thread.currentThread().getName() + " 线程开始工作！").ifPresent(System.out::println);
            try {
                Thread.sleep(15_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (CONTROLS){
                Optional.of(Thread.currentThread().getName() + " 线程工作结束！").ifPresent(System.out::println);
                CONTROLS.removeFirst();
                CONTROLS.notifyAll();
            }

        }, name);
    }

    private static class Control {}

}










