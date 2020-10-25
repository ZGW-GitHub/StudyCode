package com.code.thread.aa.thread.cc.methods;

/**
 * @author 愆凡
 *
 * t1 执行了 t2.join() 则 t1 会等待 t2 执行结束后再继续执行，在 t2 执行时可以通过 t3 对 t1 执行 t1.interrupt() 来使得 t1 不再等待 t2
 */
@SuppressWarnings("all")
public class Join {
	public static void main(String[] args) {

		Thread main = Thread.currentThread();

		Thread t1 = new Thread(() -> {
			while (true) {

			}
		});

		Thread t2 = new Thread(() -> {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			// 通过 t2 打断 main 的等待，来使得 main 继续执行
			main.interrupt();
		});

		t1.start();
		t2.start();

		try {
			System.out.println("即将 join");
			t1.join(); // main 会在此等待 t 执行完再继续执行
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.out.println("main 执行到了这里！");
		}

		System.out.println("main 执行完了！ ");
	}
}
