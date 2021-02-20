package com.code.dubbo.spi.wrapper;

/**
 * @author 愆凡
 * @date 2021/2/20 11:43
 */
public class WrapperImplA implements Wrapper {

	private final Wrapper wrapper;

	public WrapperImplA(Wrapper wrapper) {
		this.wrapper = wrapper;
	}

	@Override
	public void hello() {
		System.err.println("Wrapper Start !");

		wrapper.hello();

		System.err.println("Wrapper End !");
	}

}
