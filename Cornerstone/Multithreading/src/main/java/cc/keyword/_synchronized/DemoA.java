package cc.keyword._synchronized;

/**
 * @author 愆凡
 * @date 2020/5/7 2:28 下午
 */
public class DemoA {

	private static final Object LOCK = new Object();

	public static void main(String[] args) {

		new Thread(DemoA::work, "Thread-A").start();
		new Thread(DemoA::work, "Thread-B").start();

	}

	private static void work() {
		synchronized (LOCK) {
			System.out.println(Thread.currentThread().getName() + " 抢到了锁！");
			try {
				Thread.sleep(3_000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + " 释放了锁！");
		}
	}

}
