package com.code.mq.rocketmq.basic.msgtype;

import com.code.mq.rocketmq.RocketMqApplicationTest;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;

import java.nio.charset.Charset;
import java.util.stream.Collectors;

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
	public void orderTest() throws InterruptedException, MQClientException {
		DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(group);

		// 设置消费模式为顺序消费，编码无法实现，需要注解方式来实现
//		consumer.registerMessageListener(new DefaultRocketMQListenerContainer().new DefaultMessageListenerOrderly());

		consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);

		consumer.subscribe(topic, "*");

		consumer.registerMessageListener((MessageListenerConcurrently) (msgs, context) -> {
			System.err.println(Thread.currentThread().getName() + " consumer : " + msgs.toString() + "\n"
					+ msgs.stream().map(msg -> new String(msg.getBody(), Charset.defaultCharset())).collect(Collectors.joining("、")));

			return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
		});

		consumer.start();

		Thread.currentThread().join();
	}

	/**
	 * 消费事务消息（和消费普通消息一样）
	 * 
	 * @throws MQClientException MQClientException
	 * @throws InterruptedException InterruptedException
	 */
	@Test
	public void transactionTest() throws MQClientException, InterruptedException {
		DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(group);

		consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);

		consumer.subscribe(topic, "*");

		consumer.registerMessageListener((MessageListenerConcurrently) (msgs, context) -> {
			System.err.println(Thread.currentThread().getName() + " 消费了消息 : " + msgs.toString() + "\n消息内容"
					+ msgs.stream().map(msg -> new String(msg.getBody(), Charset.defaultCharset())).collect(Collectors.joining("、")));

			return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
		});

		consumer.start();

		Thread.currentThread().join();
	}

}
