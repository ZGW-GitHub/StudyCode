package com.code.mq.kafka.listener;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

/**
 * Kafka 消费者，其实就是一个监听类
 *
 * @author 愆凡
 * @date 2020/8/31 4:17 下午
 */
@Slf4j
@Component
public class ConsumerListener {

	@KafkaListener(topics = "${spring.kafka.template.default-topic}")
	public void listen(ConsumerRecord<String, String> record, Acknowledgment acknowledgment) {
		try {
			String message = record.value();
			log.info("收到消息: {}", message);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} finally {
			// 手动提交 offset
			acknowledgment.acknowledge();
		}
	}

}
