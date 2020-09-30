package com.code.thread.nn.threadpool.basic.ss.completable.future;

import java.util.concurrent.TimeUnit;

/**
 * @author 愆凡
 * @date 2020/4/20 11:56 上午
 */
public class RunTest {

	public static int getData() {
		try {
			System.out.println("begin");

			TimeUnit.SECONDS.sleep(2);

			System.out.println("end");
			System.out.println("------------\n");

			return 100;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return 100;
	}

	public static String getStringData() {
		try {
			System.out.println("begin");

			TimeUnit.SECONDS.sleep(2);

			System.out.println("end");
			System.out.println("------------\n");

			return "100";
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "100";
	}

	public static int throwException() {
		try {
			System.out.println("准备抛出异常");

			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("抛了");
		System.out.println("------------\n");

		throw new RuntimeException("主动抛出异常");
	}

}
