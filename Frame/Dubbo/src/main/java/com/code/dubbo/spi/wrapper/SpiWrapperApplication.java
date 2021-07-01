package com.code.dubbo.spi.wrapper;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.common.extension.ExtensionLoader;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 愆凡
 * @date 2021/7/1 11:21
 */
@Slf4j
@SpringBootApplication(scanBasePackages = "com.code.dubbo.spi.wrapper")
public class SpiWrapperApplication {
	public static void main(String[] args) {

		ExtensionLoader<ISerializable> extensionLoader = ExtensionLoader.getExtensionLoader(ISerializable.class);

		ISerializable impl = extensionLoader.getExtension("impl");
		String serialize = impl.serialize(new Object());

		System.out.println(serialize);

	}
}
