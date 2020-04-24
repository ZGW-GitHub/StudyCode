package nn.threadpool.basic.futuretask;

import java.util.concurrent.*;

/**
 * @author zgw
 * @date 2020-03-24 14:04
 **/
public class CallableWithFutureTask {
	public static void main(String[] args) throws ExecutionException, InterruptedException {

		System.out.println("main 启动");

		ExecutorService executorService = Executors.newCachedThreadPool();

		FutureTask<String> futureTask = new FutureTask<>(() -> {
			Thread.sleep(2_000);
			return "任务完成";
		});

		// 第二种方式
		// new Thread(futureTask).start();

		Future<?> submitFuture = executorService.submit(futureTask);
		executorService.shutdown();

		System.out.println("阻塞获取执行结果");
		// 返回 null
		System.out.println(submitFuture.get());
		// 返回 "任务完成"
		System.out.println(futureTask.get());

	}
}
