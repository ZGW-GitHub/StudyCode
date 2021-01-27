package com.code.thread.ee.lock.basic.locksupport;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * API：
 * <br />{@link LockSupport#park() park()} —— 阻塞当前线程，除非许可证可用
 * <br />{@link LockSupport#unpark(Thread) unpark(Thread)} —— 将指定线程的许可证设为可用
 *
 * @author 愆凡
 * @date 2020/5/11 9:14 上午
 */
@Slf4j
public class LockSupportTest {

	@Test
	public void demoTest() throws InterruptedException {
		Thread t1 = new Thread(this::work);
		t1.start();

		System.err.println("开始线程唤醒");
		LockSupport.unpark(t1);
		System.err.println("结束线程唤醒");

		Thread.currentThread().join();
	}

	private void work() {
		try {
			TimeUnit.SECONDS.sleep(1);

			System.err.println("开始线程阻塞");
			LockSupport.park();
			System.err.println("结束线程阻塞");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
