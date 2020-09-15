/*
      Date:  2019-12-17 18:47
                                 */
package come.code.dubbo.provider.config;

import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ProtocolConfig;
import org.apache.dubbo.config.ProviderConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author NotUpToYou
 */
@Configuration
public class MyProviderConfig {

    @Bean
    public ProviderConfig providerConfig() {
        return new ProviderConfig();
    }

    /**
     * 应用配置
     * @return ApplicationConfig
     */
    @Bean
    public ApplicationConfig applicationConfig() {
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName("echo-provider");
        return applicationConfig;
    }

    /**
     * 注册中心配置
     * @return RegistryConfig
     */
    @Bean
    public RegistryConfig registryConfig() {
        RegistryConfig registryConfig = new RegistryConfig();
        // 使用Zookeeper作为注册中心，并设定IP地址和端口号
        registryConfig.setProtocol("zookeeper");
        registryConfig.setAddress("127.0.0.1:2181");
        return registryConfig;
    }

    /**
     * 协议配置
     * @return ProtocolConfig
     */
    public ProtocolConfig protocolConfig() {
        ProtocolConfig protocolConfig = new ProtocolConfig();
        // 使用dubbo协议，在20880端口监听服务
        protocolConfig.setName("dubbo");
        protocolConfig.setPort(20880);
        return protocolConfig;
    }

}