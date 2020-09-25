package com.code.dubbo.consumer.config;

import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ConsumerConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 愆凡
 */
@Configuration
public class MyConsumerConfig {

	@Bean
	ApplicationConfig applicationConfig() {
		ApplicationConfig applicationConfig = new ApplicationConfig();
		applicationConfig.setName("echo-dubbo.consumer");
		return applicationConfig;
	}

	@Bean
	public ConsumerConfig consumerConfig() {
		return new ConsumerConfig();
	}

	/**
	 * 注册中心配置
	 *
	 * @return RegistryConfig
	 */
	@Bean
	public RegistryConfig registryConfig() {
		RegistryConfig registryConfig = new RegistryConfig();
		registryConfig.setProtocol("zookeeper");
		registryConfig.setAddress("127.0.0.1:2181");
		return registryConfig;
	}

}
