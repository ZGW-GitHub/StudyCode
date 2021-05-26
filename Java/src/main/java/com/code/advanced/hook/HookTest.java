package com.code.advanced.hook;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * @author 愆凡
 * @date 2021/5/26 10:51
 */
@Slf4j
public class HookTest {

	@Test
	public void tset() throws InterruptedException {
		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			log.debug("钩子被调用");

			try {
				TimeUnit.SECONDS.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			log.debug("钩子函数执行结束");
		}));

		log.info("Java 程序即将结束");
		TimeUnit.SECONDS.sleep(1);
	}

}
