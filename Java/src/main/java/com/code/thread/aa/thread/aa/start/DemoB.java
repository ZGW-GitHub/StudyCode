package com.code.thread.aa.thread.aa.start;

/**
 * 通过创建类实现 Runnable 接口，来启动一个线程
 *
 * @author 愆凡
 * @date 2019-11-30 15:35
 */
public class DemoB {

	public static void main(String[] args) {

		new Thread(new MyRunnable()).start();

	}

	private static class MyRunnable implements Runnable {
		@Override
		public void run() {
			// 线程需要执行的业务逻辑
		}
	}

}
