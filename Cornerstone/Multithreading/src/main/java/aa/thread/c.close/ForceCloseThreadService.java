package aa.thread.c.close;

/**
 * @author NotUpToYou
 */
public class ForceCloseThreadService {

	/**
	 * 执行线程
	 */
	private Thread executeThread;

    private boolean finished = false;

    public void execute(Runnable task) {
        executeThread = new Thread(() -> {
            // runner 线程为实际执行任务的线程
            Thread runner = new Thread(task);
            // 将 runner 线程设置为守护线程
            runner.setDaemon(true);
            runner.start();

            try {
                // 此处 join 了”执行线程“
                runner.join(); // 此处使 runner 线程先执行，execute 线程被阻塞
                               // 如果”执行线程“被中断，此处会产生异常，异常将被捕获，”执行线程“的代码逻辑操作也完成了，”执行线程“结束，”守护线程“自然随之结束，而守护线程所运行的非常耗时的任务也将消亡了 。
                finished = true;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        executeThread.start();
    }

    public void shutdown(long mills) {

        long startTime = System.currentTimeMillis();

        while (!finished) {
            if ((System.currentTimeMillis() - startTime) >= mills) {
                System.out.println("任务超时，需要结束它！");
                executeThread.interrupt(); // 对“执行线程”执行中断操作
                break;
            }

            try {
                executeThread.sleep(1);
            } catch (InterruptedException e) {
                System.out.println("\"执行线程\"被打断！");
                break;
            }
        }

        finished = false;

    }

}
