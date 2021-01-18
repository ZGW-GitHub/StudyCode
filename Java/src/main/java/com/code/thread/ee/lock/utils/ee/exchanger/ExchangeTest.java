package com.code.thread.ee.lock.utils.ee.exchanger;

import org.junit.Test;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;

/**
 * 注意：
 * 在构造方法中添加超时，此时A会等待超时结束，但B休眠后会等待A，但A已死，所以线程B会一直wait Aa，程序会一直运行不会结束
 * exchange方法接收到的是同一个对象
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
