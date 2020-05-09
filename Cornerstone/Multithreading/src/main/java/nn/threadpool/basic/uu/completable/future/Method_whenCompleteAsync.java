package nn.threadpool.basic.uu.completable.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author zgw
 * @date 2020/4/20 1:49 下午
 * <p>
 * 结果：
 * begin to start compute
 * end to compute, passed:1587361951652
 * 执行到最后一段代码了，future1 result：619
 * 执行到 whenComplete 了，result:619
 * 执行到 whenComplete 了，exception:无异常
 * 执行到最后一段代码了，future2 result：619
 * <p>
 * 结论：
 * 使用 whenCompleteAsync ：future1 、future2 异步执行
 */
public class Method_whenCompleteAsync {
	public static void main(String[] args) throws ExecutionException, InterruptedException {

		CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(RunTest::getMoreData);

		CompletableFuture<Integer> future2 = future1.whenCompleteAsync((result, excetion) -> {
			System.out.println("执行到 whenComplete 了，result:" + result);
			System.out.println("执行到 whenComplete 了，exception:" + (excetion == null ? "无异常" : excetion.getClass()));
		});

		System.out.println("执行到最后一段代码了，future1 result：" + future1.get());
		System.out.println("执行到最后一段代码了，future2 result：" + future2.get());

	}
}
