package com.code.thread.aa.thread.aa.start;

/**
 * 通过创建类继承 Thread 类来启动一个线程
 *
 * @author 愆凡
 */
public class DemoA {

	public static void main(String[] args) {

		new MyThread().start();

	}

	private static class MyThread extends Thread {
		@Override
		public void run() {
			// 线程需要执行的业务逻辑
		}
	}

}
