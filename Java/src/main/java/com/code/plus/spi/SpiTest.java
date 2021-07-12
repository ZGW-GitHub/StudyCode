package com.code.plus.spi;

import org.junit.Test;

import java.util.ServiceLoader;

/**
 * @author 愆凡
 * @date 2021/2/19 15:46
 */
public class SpiTest {

	/**
	 * Java SPI 示例
	 */
	@Test
	public void javaServiceLoaderTest() {
		ServiceLoader<ServiceDemo> serviceLoader = ServiceLoader.load(ServiceDemo.class, Thread.currentThread().getContextClassLoader());

		serviceLoader.forEach(System.err::println);
	}

}
