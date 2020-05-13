package nn.threadpool.basic.ss.completable.future;

import java.util.Random;

/**
 * @author 愆凡
 * @date 2020/4/20 11:56 上午
 */
public class RunTest {

	private static final Random RANDOM = new Random();

	public static int getMoreData() {
		System.out.println("begin to start compute");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		System.out.println("end to compute, passed:" + System.currentTimeMillis());
		return RANDOM.nextInt(1000);
	}

	public static int throwException() {
		System.out.println("准备抛出异常");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		System.out.println("抛了");
		throw new RuntimeException("主动抛出异常");
	}

}
