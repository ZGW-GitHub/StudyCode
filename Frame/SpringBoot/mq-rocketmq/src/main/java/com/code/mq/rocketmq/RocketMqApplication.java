package com.code.mq.rocketmq;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;

/**
 * @author 愆凡
 * @date 2020/12/31 11:44
 */
@SpringBootApplication
public class RocketMqApplication {

	public static void main(String[] args) {
		SpringApplication.run(RocketMqApplication.class, args);
	}

	@Service
	@RocketMQMessageListener(topic = "${rocketmq.producer.customized-trace-topic}", consumerGroup = "${rocketmq.producer.group}")
	class StringConsumer implements RocketMQListener<String> {
		@Override
		public void onMessage(String message) {
			System.err.println("Consumer msg : " + message);
		}
	}

}
