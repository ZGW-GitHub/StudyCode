package com.code.dubbo.spi;

import com.code.dubbo.DubboApplicationTest;
import com.code.dubbo.spi.simple.Spi;
import com.code.dubbo.spi.wrapper.Wrapper;
import org.apache.dubbo.common.extension.ExtensionLoader;
import org.junit.Test;

/**
 * @author 愆凡
 * @date 2021/2/20 11:46
 */
public class SpiTest extends DubboApplicationTest {

	/**
	 * 普通扩展测试
	 */
	@Test
	public void simpleTest() {
		ExtensionLoader<Spi> extensionLoader = ExtensionLoader.getExtensionLoader(Spi.class);

		Spi spiA = extensionLoader.getExtension("SpiA");
		spiA.hello();

		Spi spiB = extensionLoader.getExtension("SpiB");
		spiB.hello();

	}

	/**
	 * 普通扩展测试
	 */
	@Test
	public void wrapperTest() {
		ExtensionLoader<Wrapper> extensionLoader = ExtensionLoader.getExtensionLoader(Wrapper.class);

		Wrapper wrapper = extensionLoader.getExtension("WrapperB");
		wrapper.hello();
	}

}
