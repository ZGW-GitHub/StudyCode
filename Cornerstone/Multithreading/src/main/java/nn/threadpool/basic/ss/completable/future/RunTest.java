package nn.threadpool.basic.ss.completable.future;

/**
 * @author 愆凡
 * @date 2020/4/20 11:56 上午
 */
public class RunTest {

	public static int getMoreData() {
		System.out.println("begin");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		System.out.println("end\n");
		return 100;
	}

	public static int throwException() {
		System.out.println("准备抛出异常");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		System.out.println("抛了\n");
		throw new RuntimeException("主动抛出异常");
	}

}
