package com.code.thread.cc.keyword._synchronized;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * @author 愆凡
 * @date 2020/5/7 2:28 下午
 */
@Slf4j
public class SynchronizedTest {

	/**
	 * 锁对象
	 */
	public final Object MONITOR = SynchronizedTest.class;

	@Test
	public void demoTest() throws InterruptedException {
		new Thread(this::work, "T1").start();
		new Thread(this::work, "T2").start();

		Thread.currentThread().join();
	}

	private void work() {
		synchronized (MONITOR) {
			System.out.println(Thread.currentThread().getName() + " 抢到了锁！");
			try {
				Thread.sleep(3_000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + " 释放了锁！");
		}
	}


	/**
	 * 用来存储生产的数据
	 */
	private int num = 0;
	/**
	 * 标识是否有已生产的数据
	 */
	private volatile boolean isHave = false;

	/**
	 * 一个生产者、消费者示例
	 */
	@Test
	public void consumerTest() throws InterruptedException {
		new Thread(this::provider, "T1").start();
		new Thread(this::consumer, "T2").start();

		Thread.currentThread().join();
	}

	private void provider() {
		synchronized (MONITOR) {
			while (true) {
				try {
					if (isHave) {
						MONITOR.wait();
					}

					TimeUnit.SECONDS.sleep(1);
					System.err.println(Thread.currentThread().getName() + "，生产了 ：" + ++num);

					isHave = true;
				} catch (InterruptedException e) {
					log.error("生产者发生异常 : ", e);
				} finally {
					MONITOR.notify();
				}
			}
		}
	}

	private void consumer() {
		synchronized (MONITOR) {
			while (true) {
				try {
					if (!isHave) {
						MONITOR.wait();
					}

					TimeUnit.SECONDS.sleep(1);
					System.err.println(Thread.currentThread().getName() + "，消费了 ：" + num);

					isHave = false;
				} catch (InterruptedException e) {
					log.error("消费者发生异常 : ", e);
				} finally {
					MONITOR.notify();
				}
			}
		}
	}

}
