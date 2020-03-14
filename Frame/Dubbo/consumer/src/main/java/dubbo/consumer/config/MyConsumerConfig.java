/*
      Date:  2019-12-17 19:15
                                 */
package dubbo.consumer.config;

import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ConsumerConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author NotUpToYou
 */
@Configuration
public class MyConsumerConfig {

    @Bean
    ApplicationConfig applicationConfig() {
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName("echo-consumer");
        return applicationConfig;
    }

    @Bean
    public ConsumerConfig consumerConfig() {
        return new ConsumerConfig();
    }

    /**
     * 注册中心配置
     * @return RegistryConfig
     */
    @Bean
    public RegistryConfig registryConfig() {
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setProtocol("zookeeper");
        registryConfig.setAddress("wsl:2181");
        return registryConfig;
    }

}
