package nn.threadpool.basic.ss.completable.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author zgw
 * @date 2020/4/21 3:06 下午
 */
public class Method_exceptionally_2 {
	public static void main(String[] args) throws ExecutionException, InterruptedException {

		CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(RunTest::throwException);

		CompletableFuture<Integer> future2 = future1.whenCompleteAsync((result, exception) -> {
			System.out.println("计算已执行完毕，result:" + result);
			System.out.println("计算已执行完毕，exception:" + (exception == null ? "无异常" : exception.getClass()));
		});

		CompletableFuture<Integer> future3 = future2.exceptionally(exception -> {
			System.out.println("计算执行过程中发生了异常，exception:" + exception.getClass());
			return 0;
		});

		System.out.println("执行到最后一段代码了，future result：" + future1.get());

		// 因为上面的执行过程中，已经抛出了异常了，那么下面的这两段代码是无法执行到的
		System.out.println("执行到最后一段代码了，future2 result：" + future2.get());
		System.out.println("执行到最后一段代码了，future3 result：" + future3.get());

	}
}
