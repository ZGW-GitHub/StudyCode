package e.future.vs.completablefuture;

/********************************
 *   Time:  2019-07-23 22:49    *
 ********************************/

public class Executor笔记 {

    /*
     *
     * int corePoolSize,                        核心线程数
     * int maximumPoolSize,                     最大线程数
     * long keepAliveTime,                      当线程数大于核心线程数时，多余的空闲线程在终止之前等待新任务的最长时间
     * TimeUnit unit,                           时间单位
     * BlockingQueue<Runnable> workQueue,       block队列
     * ThreadFactory threadFactory,             执行程序创建新线程时使用的工厂
     * RejectedExecutionHandler handler         当线程数量已达到线程边界和队列容量最大值时，阻止线程使用的处理程序
     *
     *  注意：
     *      核心线程满了，任务会放入等待队列，等待队列满了，才会继续增加线程。
     *      核心线程一旦创建就不会销毁。
     *          除非使线程池shutDown、或设置了 ExecutorService.allowCoreThreadTimeOut(true) 且 KeepAliveTime 非 0 。
     *
     *
     *
     *      init = 0 （创建时初始化 0 个线程）
     *      提交 < 核心线程数
     *          创建线程数 = 提交数
     *      提交 > 核心线程数
     *          放入等待队列
     *      提交 >= 核心线程数 + 等待队列大小
     *          创建线程数++ ，直到创建线程数 = 最大线程数
     *      提交 > 最大线程数 + 等待队列大小
     *          执行”拒绝策略“
     *      提交 < 最大线程数
     *          这些线程（最大线程数 - 提交），将在空闲时间 > keepAliveTime 时被销毁
     *
     *
     *
     *
     *
     * ------------------------------------------------------------------------------------------
     *
     * shutDown()
     *      20 Threads
     *          10 is working
     *          10 is idle（空闲）
     *
     *      shutDown
     *
     *      10 waiting to finished the work
     *      10 Threads is interruped（中断）
     *      20 will exit
     *
     *
     * shutDownNow()
     *      20 Threads
     *          10 is working
     *          10 in queue
     *
     *      shutDown
     *
     *      10 return list<Runnable> 10 Thread`s runnable
     *      20 Threads is interruped（中断）
     *
     *
     * 所谓的线程池 shutDown 即为：线程池中创建的线程数为 0 。
     *      此条件可以在使用 Executors 工具类创建某些指定类型的线程池时可达到
     *      或
     *      设置了核心线程的回收为 true（ ExecutorService.allowCoreThreadTimeOut(true) ）
     *          注意：此时 KeepAliveTime 不能为 0
     *
     *
     *
     * ------------------------------------------------------------------------------------------
     *
     * Executors 工具类
     *
     *      cached          缓存
     *      fixed           固定（x）
     *      scheduled       计划
     *      single          单
     *      work stealing   偷工作
     *
     *
     *
     *
     * ------------------------------------------------------------------------------------------
     *
     * TimerScheduler 计时器调度程序
     *
     *                                  任务时间大于循环时间，下一次循环是否会推迟：
     *      Timer / TimerTask                         会推迟
     *
     *      crontab（Linux）                         不会推迟
     *
     *      quartz                                  不会推迟
     *              很灵活，推荐
     *
     *      SchedulerExecutorService                 会推迟
     *
     *      cron4j
     *
     *
     */

}
