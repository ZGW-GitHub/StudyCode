package com.code.dubbo.consumer.config;

import lombok.Data;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ConsumerConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 愆凡
 */
@Data
@Configuration
public class MyConsumerConfig {

	@Value("${spring.application.name}")
	private String applicationName;

	@Bean
	public ApplicationConfig applicationConfig() {
		ApplicationConfig applicationConfig = new ApplicationConfig();
		applicationConfig.setName(applicationName);
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
		registryConfig.setAddress("linux.juststudy.site:12181");
		return registryConfig;
	}

	@Bean
	public ConsumerConfig consumerConfig() {
		ConsumerConfig consumerConfig = new ConsumerConfig();
		consumerConfig.setGroup("dubbo-demo");
//		consumerConfig.setFilter("demoFilter");
		return consumerConfig;
	}

}
