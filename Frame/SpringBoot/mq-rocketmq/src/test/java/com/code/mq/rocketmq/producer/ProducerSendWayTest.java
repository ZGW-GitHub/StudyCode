package com.code.mq.rocketmq.producer;

import com.code.mq.rocketmq.RocketMqApplicationTest;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

/**
 * RocketMq 发送消息的三种方式：同步发送、异步发送、单向发送
 *
 * @author 愆凡
 * @date 2020/12/31 11:36
 */
public class ProducerSendWayTest extends RocketMqApplicationTest {

	@Autowired
	private RocketMQTemplate rocketMQTemplate;

	@Value("${rocketmq.producer.customized-trace-topic}")
	private String topic;

	/**
	 * 同步发送
	 */
	@Test
	public void syncSendTest() throws InterruptedException {
		SendResult sendResult = rocketMQTemplate.syncSend(topic, "Sync 消息");

		System.err.println("sendResult : " + sendResult);
	}

	/**
	 * 异步发送
	 */
	@Test
	public void asyncSendTest() {
		rocketMQTemplate.asyncSend(topic, "Async 消息", new SendCallback() {
			@Override
			public void onSuccess(SendResult sendResult) {
				System.err.println("SendResult : " + sendResult);
			}

			@Override
			public void onException(Throwable throwable) {
				System.err.println("Error : " + throwable.getMessage());
			}
		}, 3_000);
	}

	/**
	 * 单向发送
	 */
	@Test
	public void oneWaySendTest() throws InterruptedException {
		// 由于在OneWay方式发送消息时没有请求应答处理，一旦出现消息发送失败，则会因为没有重试而导致数据丢失。
		// 若数据不可丢，建议选用可靠同步或可靠异步发送方式。
		rocketMQTemplate.sendOneWay(topic, "OneWay 消息");

		Thread.sleep(3_000);
	}

	@Test
	public void timeMessageTest() {
//		rocketMQTemplate.sendAndReceive();
//		rocketMQTemplate.sendMessageInTransaction();
//		rocketMQTemplate.convertAndSend();
	}

}
