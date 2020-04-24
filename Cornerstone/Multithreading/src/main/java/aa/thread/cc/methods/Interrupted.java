package aa.thread.cc.methods;

/**
 * Thread 类的实例方法：interrupt()		设置中断标记
 * <p>
 * Thread 类的实例方法：isInterrupted()	不清除中断标记
 * Thread 类的静态方法：interrupted()		清除中断标记
 *
 * @author NotUpToYou
 */
public class Interrupted {

	private static final Object MONITOR = new Object();

	public static void main(String[] args) throws InterruptedException {

		Thread t = new Thread(() -> {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				System.out.println(">> 收到中断信号。");
				e.printStackTrace();
			}
			System.out.println("中断之后，可继续做事！");
		});

		t.start();
		Thread.sleep(1000);

		// false : 线程未被中断
		System.out.println("中断前，t.isInterrupted() ：" + t.isInterrupted());
		// false : 线程未被中断，不用清除标记
		System.out.println("中断前，Thread.interrupted() ：" + Thread.interrupted());

		// 通知线程中断
		t.interrupt();

		// 下面四句输出别同时打开，因为重排序导致它们的执行顺序不一定，从而导致结果不是预期的，可以每次打开一组

		// 都应该返回 true ，但因 InterruptedException 异常被捕获，所以这里都返回 false
		System.out.println("中断后，t.isInterrupted() ：" + Thread.currentThread().isInterrupted());
		System.out.println("中断后，t.isInterrupted() ：" + Thread.currentThread().isInterrupted());

		// 第一个应返回 true，第二个应返回 false ，同样因为 InterruptedException 异常被捕获，所以这里都返回 false
		System.out.println("中断后，Thread.interrupted() ：" + Thread.interrupted());
		System.out.println("中断后，Thread.interrupted() ：" + Thread.interrupted());


	}

}
