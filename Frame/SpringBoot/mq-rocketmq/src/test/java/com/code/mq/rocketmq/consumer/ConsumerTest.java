package com.code.mq.rocketmq.consumer;

import com.code.mq.rocketmq.RocketMqApplicationTest;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.spring.support.DefaultRocketMQListenerContainer;
import org.junit.Test;
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

	@Test
	public void consumerTest() throws InterruptedException {
		DefaultRocketMQListenerContainer container = new DefaultRocketMQListenerContainer();


		container.setConsumer(new DefaultMQPushConsumer(group));

		container.setNameServer("linux.notuptoyou.site:9876");

		container.setTopic(topic);

		container.setConsumerGroup(group);


		container.start();

		Thread.currentThread().join();
	}

}
