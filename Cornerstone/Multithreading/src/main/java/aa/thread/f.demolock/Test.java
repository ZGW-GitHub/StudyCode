package aa.thread.f.demolock;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author NotUpToYou
 */
public class Test {

    public static void main(String[] args) {

        final BooleanLock booleanLock = new BooleanLock();

        Stream.of("T1", "T2", "T3", "T4")
                .forEach(name ->
                        new Thread(() -> {
                            try {
                                // 去获取锁
                                booleanLock.lock();
                                System.out.println(Thread.currentThread().getName() + " 获取到了锁！");
                                work();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            } finally {
                                // 释放锁
                                booleanLock.unlock();
                            }
                        }, name).start()
                );

        booleanLock.unlock();

    }

    private static void work() throws InterruptedException {

        Optional.of(Thread.currentThread().getName() + " 正在工作。。。")
                .ifPresent(System.out::println);
        // 假装工作
        Thread.sleep(5_000);

    }

}
