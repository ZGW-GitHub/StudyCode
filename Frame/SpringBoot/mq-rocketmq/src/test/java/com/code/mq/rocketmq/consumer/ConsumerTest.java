package com.code.mq.rocketmq.consumer;

import com.code.mq.rocketmq.RocketMqApplicationTest;
import org.apache.rocketmq.spring.support.DefaultRocketMQListenerContainer;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author 愆凡
 * @date 2021/1/11 15:29
 */
public class ConsumerTest extends RocketMqApplicationTest {

	@Value("${rocketmq.producer.customized-trace-topic}")
	private String topic;

	@Value("${rocketmq.producer.group}")
	private String group;

	public void consumerTest() throws InterruptedException {
		DefaultRocketMQListenerContainer container = new DefaultRocketMQListenerContainer();

		container.setRocketMQListener(message -> System.err.println("Consumer msg : " + message));

		container.start();

		Thread.currentThread().join();
	}

}
