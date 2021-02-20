package com.code.dubbo.spi.simple;

/**
 * @author 愆凡
 * @date 2021/2/20 11:43
 */
public class SpiImplB implements Spi {

	@Override
	public void hello() {
		System.err.println("This is B !");
	}

}
