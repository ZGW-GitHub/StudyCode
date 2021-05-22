package com.code.dubbo.provider.config;

import lombok.Data;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ProtocolConfig;
import org.apache.dubbo.config.ProviderConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 愆凡
 */
@Data
@Configuration
public class MyProviderConfig {

	@Value("${spring.application.name}")
	private String applicationName;

	/**
	 * 应用配置
	 *
	 * @return ApplicationConfig
	 */
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
		// 使用Zookeeper作为注册中心，并设定IP地址和端口号
		registryConfig.setProtocol("zookeeper");
//		registryConfig.setAddress("linux.notuptoyou.site:12181");
		registryConfig.setAddress("101.37.65.146:12181");
		return registryConfig;
	}

	/**
	 * 协议配置
	 *
	 * @return ProtocolConfig
	 */
	@Bean
	public ProtocolConfig protocolConfig() {
		ProtocolConfig protocolConfig = new ProtocolConfig();
		// 使用dubbo协议，在20880端口监听服务
		protocolConfig.setName("dubbo");
		protocolConfig.setPort(20880);
		return protocolConfig;
	}

	@Bean
	public ProviderConfig providerConfig() {
		ProviderConfig providerConfig = new ProviderConfig();
		providerConfig.setGroup("dubbo-demo");
		providerConfig.setFilter("demoFilter");
		return providerConfig;
	}

}
