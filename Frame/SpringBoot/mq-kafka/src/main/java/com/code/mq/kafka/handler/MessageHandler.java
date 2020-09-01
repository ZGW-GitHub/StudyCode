package com.code.mq.kafka.handler;

import com.code.mq.kafka.constants.KafkaConsts;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

/**
 * 消息处理器
 *
 * @author 愆凡
 * @date 2020/8/31 4:17 下午
 */
@Slf4j
@Component
public class MessageHandler {

	@KafkaListener(topics = KafkaConsts.TOPIC_TEST, containerFactory = "ackContainerFactory")
	public void handleMessage(ConsumerRecord<String, String> record, Acknowledgment acknowledgment) {
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
