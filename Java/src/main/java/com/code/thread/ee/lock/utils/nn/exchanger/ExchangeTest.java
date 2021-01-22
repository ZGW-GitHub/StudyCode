package com.code.thread.ee.lock.utils.nn.exchanger;

import org.junit.Test;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;

/**
 * API：
 * <br />{@link Exchanger#exchange(Object) exchange(Object obj)} —— 交换数据并等待对方调用exchange
 *
 * @author 愆凡
 */
public class ExchangeTest {

	private final Exchanger<String> exchanger = new Exchanger<>();

	@Test
	public void test() throws InterruptedException {

		new Thread(this::doExchange, "T1").start();
		new Thread(this::doExchange, "T2").start();

		TimeUnit.SECONDS.sleep(2);
	}

	public void doExchange() {
		try {

			TimeUnit.SECONDS.sleep(1);

			String result = exchanger.exchange("my name is " + Thread.currentThread().getName());

			System.err.println(Thread.currentThread().getName() + " 收到：" + result);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
