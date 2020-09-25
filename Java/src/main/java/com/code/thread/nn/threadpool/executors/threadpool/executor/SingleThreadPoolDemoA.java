package com.code.thread.nn.threadpool.executors.threadpool.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author 愆凡
 */
public class SingleThreadPoolDemoA {

	public static void main(String[] args) throws InterruptedException {

		ExecutorService executorService = Executors.newSingleThreadExecutor();

		// 不能输出了，因为获取 SingleThreadExecutor 时被包装了，不能强转为 ThreadPoolExecutor
//        System.out.println(((ThreadPoolExecutor)executorService).getActiveCount()); // 0

		IntStream.range(0, 100).boxed().forEach(integer -> executorService.execute(() -> {
			try {
				TimeUnit.SECONDS.sleep(2);
				System.out.println(Thread.currentThread().getName() + " is ok !");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}));
		TimeUnit.SECONDS.sleep(1);

		// 不能输出了，因为获取 SingleThreadExecutor 时被包装了，不能强转为 ThreadPoolExecutor
//        System.out.println(((ThreadPoolExecutor)executorService).getActiveCount()); // 1

	}

}
