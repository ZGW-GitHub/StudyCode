package e.future.vs.completablefuture.future;

import java.util.concurrent.*;

/**
 * cancel(false) 与 cancel(true）的区别在于：
 *      cancel(false) 只取消已经提交但还没有被运行的任务（即任务就不会被安排运行）；
 *      cancel(true) 会取消所有已经提交的任务，包括 正在等待的 和 正在运行的 任务。
 * cancel(true) 方法的原理是向正在运行任务的线程发送中断指令 —— 即调用运行任务的 Thread 的 interrupt() 方法。
 *      所以：如果一个任务是可取消的，那么它应该可以对 Thread 的 interrupt() 方法做出被取消时的响应。
 *
 * 调用 cancel 返回 false ：
 *      任务已经结束了
 *      已经被 cancel 过了
 *
 * 调用 cancel 后，isDone() 返回 true 。isCancelled() 返回 true 。
 */
public class A_Api_Cancel {

    private static final ExecutorService executorService = Executors.newCachedThreadPool();

    public static void main(String[] args) throws InterruptedException, ExecutionException {

//        testCancel1();
//        testCancel2();
        testCancel3();

    }

    private static void testCancel1() {

        Future<Integer> future = executorService.submit(() -> 1);

        System.out.println(future.cancel(true)); // true
        System.out.println(future.isDone()); // true
        System.out.println(future.isCancelled()); // true

    }

    private static void testCancel2() throws InterruptedException {

        Future<Integer> future = executorService.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 2;
        });

        TimeUnit.MILLISECONDS.sleep(100);
        System.out.println(future.cancel(true)); // true
        System.out.println(future.cancel(true)); // false
        System.out.println(future.isDone()); // true
        System.out.println(future.isCancelled()); // true

    }

    private static void testCancel3() throws InterruptedException, ExecutionException {

        Future<Integer> future = executorService.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
                System.out.println("Cancel 后，线程被中断。");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("我执行了。。。");
            return 2;
        });

        TimeUnit.MILLISECONDS.sleep(100);
        System.out.println(future.cancel(true)); // true
        System.out.println(future.isDone()); // true
        System.out.println(future.isCancelled()); // true
        System.out.println(future.get()); // 不能取出结果，抛异常

    }

}
