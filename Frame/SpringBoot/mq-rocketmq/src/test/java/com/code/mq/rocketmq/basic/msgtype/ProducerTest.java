package com.code.mq.rocketmq.basic.msgtype;

import com.alibaba.fastjson.JSONObject;
import com.code.mq.rocketmq.RocketMqApplicationTest;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.client.producer.selector.SelectMessageQueueByHash;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * 四种消息类型：顺序消息、定时消息、事务消息
 *
 * @author 愆凡
 * @date 2021/1/14 17:23
 */
@Slf4j
public class ProducerTest extends RocketMqApplicationTest {

	@Autowired
	private RocketMQTemplate rocketMQTemplate;

	@Value("${rocketmq.producer.customized-trace-topic}")
	private String topic;

	/**
	 * 顺序消息：部分有序
	 */
	@Test
	public void orderTest() {
		IntStream.range(0, 10).forEach(i -> {
			// 组装消息
			Message<String> message = MessageBuilder.withPayload("顺序消息" + i).build();
			// 设置队列选择策略
			rocketMQTemplate.setMessageQueueSelector(new SelectMessageQueueByHash());
			// 发送消息
			SendResult sendResult = rocketMQTemplate.syncSendOrderly(topic, message, "hashKey");

			System.err.println(sendResult);
		});
	}

	/**
	 * 定时消息
	 */
	@Test
	public void timeTest() {
		// 组装消息
		Message<String> message = MessageBuilder.withPayload("定时消息").build();
		// 发送定时消息，延迟级别为3，即：10秒
		SendResult sendResult = rocketMQTemplate.syncSend(topic, message, TimeUnit.MINUTES.toMillis(3), 3);

		System.err.println(sendResult);
	}

	/**
	 * 事务消息
	 */
	@Test
	public void transactionalTest() {
		// 组装消息
		Message<String> message = MessageBuilder.withPayload("事务消息").build();
		// 发送事务消息
		TransactionSendResult sendResult = rocketMQTemplate.sendMessageInTransaction(topic, message, new JSONObject());

		System.err.println(sendResult);
	}

}
