package com.code.mq.rocketmq.config;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 愆凡
 * @date 2020/12/31 11:38
 */
@Configuration
public class ProducerConfig {

	@Bean(name = "producer")
	@ConfigurationProperties(prefix = "rocketmq.producer")
	public DefaultMQProducer defaultMqProducer() {
		return new DefaultMQProducer();
//		// 构建生产者实例
//		DefaultMQProducer producer = new DefaultMQProducer();
//		
//		producer.setProducerGroup("group_name_sync");
//		// 设置发送消息失败重试次数
//		producer.setRetryTimesWhenSendFailed(3);
//		// 设置NameServer地址
//		producer.setNamesrvAddr("192.168.56.101:9876;192.168.56.102:9876");
	}

}
