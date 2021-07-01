package com.code.dubbo.spi.simple;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.common.extension.ExtensionLoader;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author 愆凡
 * @date 2021/7/1 11:19
 */
@Slf4j
@SpringBootApplication(scanBasePackages = "com.code.dubbo.spi.simple")
public class SpiSimpleApplication {
	public static void main(String[] args) {

		ConfigurableApplicationContext context = new SpringApplicationBuilder(SpiSimpleApplication.class).run();

		ExtensionLoader<ISerializable> extensionLoader = ExtensionLoader.getExtensionLoader(ISerializable.class);

		ISerializable implA = extensionLoader.getExtension("implA");
		String serializeA = implA.serialize(new Object());
		System.out.println(serializeA);

		ISerializable implB = extensionLoader.getExtension("implB");
		String serializeB = implB.serialize(new Object());
		System.out.println(serializeB);

		ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
		beanFactory.registerSingleton("serializableImplA", SerializableImplA.class);
		beanFactory.registerSingleton("serializableImplB", SerializableImplB.class);

	}
}
