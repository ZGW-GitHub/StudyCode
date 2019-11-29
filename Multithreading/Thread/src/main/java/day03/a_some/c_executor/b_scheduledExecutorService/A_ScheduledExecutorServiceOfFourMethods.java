package com.a_snow.c_executor.b_scheduledExecutorService;

/* ******************************
 *   Time:  2019-07-25 17:22    *
 ****************************** */

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class A_ScheduledExecutorServiceOfFourMethods {

    private static final ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(2);


    public static void main(String[] args) throws ExecutionException, InterruptedException {

//        testScheduleWithRunnable();
//        testScheduleWithCallable();
//        testScheduleAtFixedRate();
        testScheduleWithFixedDelay();

    }

    private static void testScheduleWithRunnable() throws ExecutionException, InterruptedException {

        ScheduledFuture<?> future = scheduledThreadPoolExecutor.schedule(() -> System.out.println("执行了！"), 2, TimeUnit.SECONDS);
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(future.get());

    }

    private static void testScheduleWithCallable() throws ExecutionException, InterruptedException {

        ScheduledFuture<?> future = scheduledThreadPoolExecutor.schedule(() -> {
            System.out.println("执行了！");
            return "执行了";
        }, 2, TimeUnit.SECONDS);
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(future.get());

    }

    // 周期任务
    // 任务工作时间大于循环时间，下一次循环会延迟执行
    private static void testScheduleAtFixedRate() {

        scheduledThreadPoolExecutor.scheduleAtFixedRate(()->{
            try {
                System.out.println("工作 ：" + System.currentTimeMillis());
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, 3, 5, TimeUnit.SECONDS);

    }

    // 延迟任务（也是周期的）
    // 无论任务执行多长时间，下一次循环都要等待 delay 秒，再执行
    private static void testScheduleWithFixedDelay() {

        scheduledThreadPoolExecutor.scheduleWithFixedDelay(()->{
            try {
                System.out.println("工作 ：" + System.currentTimeMillis());
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, 3, 5, TimeUnit.SECONDS);

    }

}
