package aa.thread.uu.threadpool;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

public class MyDemoThreadPool {

    // 自定义线程池初始大小
    private final int size;

    // 默认线程池初始大小
    private final static int DEFAULT_SIZE = 10;

    // 存放 Runnable 的队列（任务队列）
    private final static LinkedList<Runnable> TASK_QUEUE = new LinkedList<>();

    // 定义线程组
    private final static ThreadGroup THREAD_GROUP = new ThreadGroup("Pool_Group");

    // 定义线程名前缀
    private final static String THREAD_PREFIX = "SIMPLE_THREAD_POOL-";

    // 线程计数
    private static volatile int seg = 0;

    // 存放 工作线程 的集合
    private final static List<WorkerTask> THREAD_QUEUE = new ArrayList<>();

    // 无参构造方法
    MyDemoThreadPool() {
        this(DEFAULT_SIZE);
    }

    // 带参构造方法
    MyDemoThreadPool(int size) {
        this.size = size;
        init();
    }

    // 线程状态枚举
    private enum TaskState {
        FREE,
        RUNNING,
        BLOCKED,
        DEAD
    }

    // 初始化方法，创建 size 个线程
    private void init() {
        for (int i = 0; i < size; i++) {
            createWorkTask();
        }
    }

    // 创建工作线程的方法
    private void createWorkTask() {
        // 创建一个工作线程
        WorkerTask task = new WorkerTask(THREAD_GROUP, THREAD_PREFIX + (seg++));
        // 启动线程
        task.start();
        // 将线程加入工作线程集合
        THREAD_QUEUE.add(task);
    }

    // 提交工作任务的方法
    public void submit(Runnable runnable) {
        synchronized (TASK_QUEUE) {
            // 将新增任务加入任务队列
            TASK_QUEUE.addLast(runnable);
            // 唤醒工作线程
            TASK_QUEUE.notifyAll();
        }
    }

    // 工作线程
    private static class WorkerTask extends Thread {

        // 当前的线程状态
        private volatile TaskState taskState = TaskState.FREE;

        // 带参构造方法
        WorkerTask(ThreadGroup group, String name) {
            super(group, name);
        }

        @Override
        public void run() {
            OUTER:
            // 判断线程是否活跃，活跃：
            while (this.taskState != TaskState.DEAD) {
                Runnable runnable;
                synchronized (TASK_QUEUE) {
                    // 判断 Runnable 队列（任务队列）是否为空，为空：
                    while (TASK_QUEUE.isEmpty()) {
                        try {
                            // 将线程阻塞
                            taskState = TaskState.BLOCKED;
                            TASK_QUEUE.wait();
                        } catch (InterruptedException e) {
                            // 产生异常 调到 OUTER 处继续执行
                            break OUTER;
                        }
                    }
                    // Runnable 队列（任务队列）不为空
                    runnable = TASK_QUEUE.removeFirst(); // 将 Runnable 队列（任务队列）第一个移除并获取
                }

                if (runnable != null) {
                    taskState = TaskState.RUNNING;
                    // 执行任务
                    runnable.run();
                    taskState = TaskState.FREE;
                }
            }
            // 线程不活跃
        }

        // 获取线程状态的 get 方法
        public TaskState getTaskState() {
            return this.taskState;
        }

        // 关闭线程的方法
        public void close() {
            this.taskState = TaskState.DEAD;
        }

    }

    public static void main(String[] args) {
        MyDemoThreadPool threadPool = new MyDemoThreadPool();
        /*for (int i = 0; i < 40; i++) {
            threadPool.submit(()->{
                System.out.println(Thread.currentThread().getName() + " 工作开始。。。");
                try {
                    Thread.sleep(1_000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " 工作结束");
            });
            System.out.println("+++++++++++");
        }*/
        IntStream.rangeClosed(1, 40)
                .forEach(i -> {
                    threadPool.submit(() -> {
                        System.out.println("任务 " + i + " 由 " + Thread.currentThread().getName() + " 负责，工作开始。。。");
                        try {
                            Thread.sleep(1_000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("任务 " + i + " 由 " + Thread.currentThread().getName() + " 负责，工作结束");
                    });
                });
    }

}
