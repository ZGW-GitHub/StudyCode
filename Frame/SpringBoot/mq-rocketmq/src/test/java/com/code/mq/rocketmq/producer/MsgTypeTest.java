package com.code.mq.rocketmq.producer;

import com.code.mq.rocketmq.RocketMqApplicationTest;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import java.util.concurrent.TimeUnit;

/**
 * 四种消息类型：广播消息、顺序消息、定时消息、事务消息
 *
 * @author 愆凡
 * @date 2021/1/14 17:23
 */
public class MsgTypeTest extends RocketMqApplicationTest {

	@Autowired
	private RocketMQTemplate rocketMQTemplate;

	@Value("${rocketmq.producer.customized-trace-topic}")
	private String topic;

	/**
	 * 顺序消息
	 */
	@Test
	public void orderTest() {

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

	}

}
