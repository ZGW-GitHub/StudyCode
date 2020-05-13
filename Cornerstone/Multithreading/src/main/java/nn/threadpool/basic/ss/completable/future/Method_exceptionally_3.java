package nn.threadpool.basic.ss.completable.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author zgw
 * @date 2020/4/21 3:06 下午
 */
public class Method_exceptionally_3 {
	public static void main(String[] args) throws ExecutionException, InterruptedException {

		CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(RunTest::throwException);

		CompletableFuture<Integer> future2 = future1.whenCompleteAsync((result, exception) -> {
			System.out.println("计算已执行完毕，result:" + result);
			System.out.println("计算已执行完毕，exception:" + (exception == null ? "无异常" : exception.getClass()));
		});

		CompletableFuture<Integer> future3 = future2.exceptionally(exception -> {
			System.out.println("计算执行过程中发生了异常，exception:" + exception.getClass());
			// 这里的返回值实际其是没有用处的。因为如果抛出了异常，future 的 get 方法是执行不到的；
			// 而如果没有抛出异常的话，还是会返回原始的 CompletableFuture 的值
			// 所以这个 exceptionally 就是仅仅用来处理异常的。
			return 0;
		});

		// System.out.println("执行到最后一段代码了，future result：" + future.get());
//		 System.out.println("执行到最后一段代码了，future2 result：" + future2.get());

		// 和 Method_exceptionally_2 唯一的区别就是注释掉了上面两段代码，但是执行结果却不一样了，而且整个 main 方法都没有抛出来异常，
		// 原因就在于 future1 和 future2 是异步执行的，所以是在别的线程抛了异常，而 main 方法是不会抛出来的。
		// 而且在获取 future3 的结果时，可以发现，返回了 future3 对象自定义的返回值
		System.out.println("执行到最后一段代码了，future3 result：" + future3.get());

	}
}
