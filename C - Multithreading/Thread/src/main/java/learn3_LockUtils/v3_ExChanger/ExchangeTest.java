package learn3_LockUtils.v3_ExChanger;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * 注意：
 *      在构造方法中添加超时，此时A会等待超时结束，但B休眠后会等待A，但A已死，所以线程B会一直wait A，程序会一直运行不会结束
 *      exchange方法接收到的是同一个对象
 */
public class ExchangeTest {

    public static void main(String[] args) throws InterruptedException {

        final Exchanger<String> exchanger = new Exchanger<>();

        new Thread(()->{
            System.out.println("Thread -> A : run...");
            try {
                // 在构造方法中添加超时
                //      注意！！！：此时A会等待超时结束，但B休眠后会等待A，但A已死，所以线程B会一直waitA，程序会一直运行不会结束
                String result = exchanger.exchange("Give you from A", 3, TimeUnit.SECONDS);
                System.out.println(Thread.currentThread().getName() + result);
            } catch (InterruptedException | TimeoutException e) {
                e.printStackTrace();
                System.out.println("超时！！！");
                System.out.println("注意！！！：此时A会等待超时结束，但B休眠后会等待A，但A已死，所以线程B会一直waitA，程序会一直运行不会结束");
            }
            System.out.println("Thread -> A : over...");
        }, "==A==").start();

        new Thread(()->{
            System.out.println("Thread -> A : run...");
            try {
                TimeUnit.SECONDS.sleep(6); // 模拟阻塞，A线程会等待B线程
                String result = exchanger.exchange("Give you from B");
                System.out.println(Thread.currentThread().getName() + result);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread -> A : over...");
        }, "==B==").start();


        TimeUnit.SECONDS.sleep(5);

    }

}
