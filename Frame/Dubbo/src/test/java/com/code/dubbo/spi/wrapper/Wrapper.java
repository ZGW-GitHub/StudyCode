package com.code.dubbo.spi.wrapper;

import org.apache.dubbo.common.extension.SPI;

/**
 * @author 愆凡
 * @date 2021/2/20 11:41
 */
@SPI
public interface Wrapper {

	default void hello() {
		System.err.println("This is SPI !");
	}

}
