package com.code.mq.kafka.config;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.listener.ContainerProperties;

/**
 * Kafka 配置类
 *
 * @author 愆凡
 * @date 2020/8/31 4:08 下午
 */
@Configuration
@EnableKafka
@EnableConfigurationProperties({KafkaProperties.class})
@AllArgsConstructor
public class KafkaConfig {

	private final KafkaProperties kafkaProperties;

	@Value("${spring.kafka.producer.properties.default-partition-num}")
	private final Integer defaultPartitionNum;

	@Bean
	public KafkaTemplate<String, String> kafkaTemplate() {
		return new KafkaTemplate<>(producerFactory());
	}

	/**
	 * 生产者工厂
	 *
	 * @return ProducerFactory
	 */
	@Bean
	public ProducerFactory<String, String> producerFactory() {
		return new DefaultKafkaProducerFactory<>(kafkaProperties.buildProducerProperties());
	}

	/**
	 * 消费者工厂
	 *
	 * @return ConsumerFactory
	 */
	@Bean
	public ConsumerFactory<String, String> consumerFactory() {
		return new DefaultKafkaConsumerFactory<>(kafkaProperties.buildConsumerProperties());
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory());
		factory.getContainerProperties().setPollTimeout(3000);
		factory.setConcurrency(defaultPartitionNum);
		factory.setBatchListener(true);
		return factory;
	}

	@Bean("ackContainerFactory")
	public ConcurrentKafkaListenerContainerFactory<String, String> ackContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory());
		factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL_IMMEDIATE);
		factory.setConcurrency(defaultPartitionNum);
		return factory;
	}

}
