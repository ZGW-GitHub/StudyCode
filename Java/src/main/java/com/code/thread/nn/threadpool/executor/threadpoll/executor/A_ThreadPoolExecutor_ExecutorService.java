package com.code.thread.nn.threadpool.executor.threadpoll.executor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * int corePoolSize,                        核心线程数（当等待对列“满了”才会增加线程）
 * int maximumPoolSize,                     最大线程数
 * long keepAliveTime,                      当线程数大于核心线程数时，多余的空闲线程在终止之前等待新任务的最长时间
 * TimeUnit unit,                           时间单位
 * BlockingQueue<Runnable> workQueue,       block队列
 * ThreadFactory threadFactory,             执行程序创建新线程时使用的工厂
 * RejectedExecutionHandler handler         当线程数量已达到线程边界和队列容量最大值时，阻止线程使用的处理程序
 *
 * @author NotUpToYou
 */
public class A_ThreadPoolExecutor_ExecutorService {

    private static final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
            1,
            2,
            30,
            TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(1),
            (ThreadFactory) Thread::new,
            new ThreadPoolExecutor.AbortPolicy());

    public static void main(String[] args) {

        System.out.println("线程池初始容量：" + threadPoolExecutor.getCorePoolSize());
        System.out.println("线程池最大容量：" + threadPoolExecutor.getMaximumPoolSize());

        threadPoolExecutor.execute(()-> submitDemo(10, "T1"));
        threadPoolExecutor.execute(()-> submitDemo(1 , "T2"));
        threadPoolExecutor.execute(()-> submitDemo(10,  "T3"));
//        executorService.execute(()-> submitDemo(10,  "T4"));

        int activeCount = -1;
        int queueSize = -1;
        while (true){
            if (activeCount != threadPoolExecutor.getActiveCount() || queueSize != threadPoolExecutor.getQueue().size()) {
                System.out.println("活动线程的大致数量：" + threadPoolExecutor.getActiveCount());
                System.out.println("等待队列中的线程个数：" + threadPoolExecutor.getQueue().size());
                activeCount = threadPoolExecutor.getActiveCount();
                queueSize = threadPoolExecutor.getQueue().size();
            }
        }

    }

    private static void submitDemo(long time, String name) {
        try {
            System.out.println(name + " ---> 执行了：");
            TimeUnit.SECONDS.sleep(time);
            System.out.println(name + " ---> 结束了。");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
