package com.code.thread.aa.thread.cc.methods;

/**
 * @author 愆凡
 */
@SuppressWarnings("all")
public class Wait {
	public static void main(String[] args) throws InterruptedException {

		final Thread t1 = new Thread(() -> {
			try {
				Thread.currentThread().wait();
				System.out.println("t OK");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});

		Thread t2 = new Thread(() -> {
			try {
				t1.join();
				Thread.sleep(5_000);
				System.out.println("t2 OK");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});

		t1.start();
		t2.start();

		Thread.sleep(1_000);

		System.out.println(t1.getState());
		t1.join();

	}
}
