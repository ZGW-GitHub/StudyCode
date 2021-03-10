package com.code.mq.rocketmq.sentinel;

import com.code.mq.rocketmq.RocketMqApplicationTest;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.nio.charset.Charset;
import java.util.stream.Collectors;

/**
 * 使用阿里 Sentinel 进行消费者限流
 *
 * @author 愆凡
 * @date 2021/3/10 09:18
 */
public class SentinelTest extends RocketMqApplicationTest {

	@Autowired
	private RocketMQTemplate rocketMQTemplate;

	@Value("${rocketmq.producer.customized-trace-topic}")
	private String topic;

	@Value("${rocketmq.producer.group}")
	private String group;

	@Test
	public void producerTest() {
		SendResult sendResult = rocketMQTemplate.syncSend(topic, "Sync 消息");

		System.err.println("sendResult : " + sendResult);
	}

	@Test
	public void consumerTest() throws MQClientException, InterruptedException {
		DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(group);

		consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
		consumer.subscribe(topic, "*");

		consumer.registerMessageListener((MessageListenerConcurrently) (msgs, context) -> {
			System.err.println(Thread.currentThread().getName() + " consumer : " + msgs.toString() + "\n"
					+ msgs.stream().map(msg -> new String(msg.getBody(), Charset.defaultCharset())).collect(Collectors.joining("、")));

			return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
		});

		consumer.start();

		Thread.currentThread().join();
	}

}
