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
	public ApplicationConfig applicationConfig() {
		ApplicationConfig applicationConfig = new ApplicationConfig();
		applicationConfig.setName("echo-dubbo.consumer");
		return applicationConfig;
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
		registryConfig.setAddress("linux.notuptoyou.site:2181");
		return registryConfig;
	}

	@Bean
	public ConsumerConfig consumerConfig() {
		return new ConsumerConfig();
	}

}
