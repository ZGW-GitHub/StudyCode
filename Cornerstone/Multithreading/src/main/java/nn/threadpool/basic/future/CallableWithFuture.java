package nn.threadpool.basic.future;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author zgw
 * @date 2020-03-24 13:41
 **/
public class CallableWithFuture {
	public static void main(String[] args) throws InterruptedException, ExecutionException {

		System.out.println("main 启动");

		ExecutorService executorService = Executors.newCachedThreadPool();
		Future<String> future = executorService.submit(() -> {
			System.out.println("任务正在执行");
			Thread.sleep(2_000);
			return "任务完成";
		});
		executorService.shutdown();

		System.out.println("阻塞地获取执行结果");
		System.out.println(future.get());

	}
}