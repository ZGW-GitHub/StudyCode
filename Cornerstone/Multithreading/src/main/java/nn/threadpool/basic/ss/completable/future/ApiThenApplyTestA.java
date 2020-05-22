package nn.threadpool.basic.ss.completable.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author 愆凡
 * @date 2020/5/21 5:25 下午
 */
public class ApiThenApplyTestA {
	public static void main(String[] args) throws ExecutionException, InterruptedException {

		CompletableFuture<Void> future1 = CompletableFuture.runAsync(RunTest::getMoreData);

		CompletableFuture<Object> future2 = future1.thenApply((result) -> {
			System.out.println("执行到 whenComplete 了，result:" + result);
			return "result : " + result;
		});

		System.out.println("执行到最后一段代码了，future1 result：" + future1.get());
		System.out.println("执行到最后一段代码了，future2 result：" + future2.get());

	}
}
