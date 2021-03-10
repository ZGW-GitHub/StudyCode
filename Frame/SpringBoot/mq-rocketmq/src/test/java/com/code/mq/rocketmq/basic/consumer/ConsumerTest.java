package com.code.mq.rocketmq.basic.consumer;

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
@SuppressWarnings("all")
public class ConsumerTest extends RocketMqApplicationTest {

	@Value("${rocketmq.producer.customized-trace-topic}")
	private String topic;

	@Value("${rocketmq.producer.group}")
	private String group;

	@Test
	public void consumerTest() throws InterruptedException, MQClientException {
		// 创建消费者
		DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(group);
		/**
		 * 设置消费的开始位置：
		 * 		CONSUME_FROM_LAST_OFFSET		一个新的订阅组第一次启动从队列的最后位置开始消费，后续再启动接着上次消费的进度开始消费
		 * 		CONSUME_FROM_FIRST_OFFSET		一个新的订阅组第一次启动从队列的最前位置开始消费，后续再启动接着上次消费的进度开始消费
		 * 		CONSUME_FROM_TIMESTAMP			一个新的订阅组第一次启动从指定时间点开始消费，后续再启动接着上次消费的进度开始消费，时间点设置参见 DefaultMQPushConsumer.consumeTimestamp 参数
		 */
		consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
		// 订阅主题（可多次订阅不同 topic）
		consumer.subscribe(topic, "*");
		// 注册监听器
		consumer.registerMessageListener((MessageListenerConcurrently) (msgs, context) -> {
			System.err.println(Thread.currentThread().getName() + " consumer : " + msgs.toString() + "\n"
					+ msgs.stream().map(msg -> new String(msg.getBody(), Charset.defaultCharset())).collect(Collectors.joining("、")));

			return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
		});
		// 启动消费者
		consumer.start();

		Thread.currentThread().join();
	}

	@Test
	public void consumer2Test() throws InterruptedException, MQClientException {
		// 创建消费者
		DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(group);
		// 设置消费的开始位置
		consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
		// 订阅主题（可多次订阅不同 topic）
		consumer.subscribe(topic, "*");
		// 注册监听器
		consumer.registerMessageListener((MessageListenerConcurrently) (msgs, context) -> {
			System.err.println(Thread.currentThread().getName() + " consumer : " + msgs.toString() + "\n"
					+ msgs.stream().map(msg -> new String(msg.getBody(), Charset.defaultCharset())).collect(Collectors.joining("、")));

			return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
		});
		// 启动消费者
		consumer.start();

		Thread.currentThread().join();
	}

}
