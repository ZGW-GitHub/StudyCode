package aa.thread.nn.thread.local;

/**
 * @author 愆凡
 * @date 2020/7/10 4:02 下午
 */
public class DemoA {
	public static void main(String[] args) {

		new Thread(() -> System.out.println(Thread.currentThread().getContextClassLoader())).start();
		new Thread(() -> System.out.println(Thread.currentThread().getContextClassLoader())).start();

	}
}
