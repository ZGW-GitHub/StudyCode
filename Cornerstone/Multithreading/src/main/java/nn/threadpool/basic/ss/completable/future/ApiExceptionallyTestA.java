package nn.threadpool.basic.ss.completable.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author zgw
 * @date 2020/4/21 3:06 下午
 */
public class ApiExceptionallyTestA {
	public static void main(String[] args) throws ExecutionException, InterruptedException {

		CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(RunTest::throwException);

		CompletableFuture<Integer> future2 = future1.whenCompleteAsync((result, exception) -> {
			System.out.println("计算已执行完毕，result : " + result);
			System.out.println("计算已执行完毕，exception : " + (exception == null ? "无异常" : exception.getClass()));
		}).exceptionally(exception -> {
			System.out.println("计算执行过程中发生了异常，exception:" + exception.getClass());
			return 0;
		});

		System.out.println("future result：" + future1.get());

	}
}
