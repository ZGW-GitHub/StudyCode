package com.code.mq.rocketmq.producer;

import com.code.mq.rocketmq.RocketMqApplicationTest;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author 愆凡
 * @date 2020/12/31 11:36
 */
public class ProducerTest extends RocketMqApplicationTest {

	@Autowired
	private RocketMQTemplate rocketMQTemplate;

	@Value("${rocketmq.producer.customized-trace-topic}")
	private String topic;

	/**
	 * 消息类型：简单消息<br />
	 * 消息发送方式：同步发送<br />
	 * 应用：可靠同步发送在众多场景中被使用，例如：重要的通知消息、短信通知、短信营销系统，等
	 */
	@Test
	public void syncProducerTest() throws InterruptedException {
		SendResult sendResult = rocketMQTemplate.syncSend(topic, "Sync 消息");

		System.err.println("sendResult : " + sendResult);

		Thread.sleep(3_000);
	}

	/**
	 * 消息类型：简单消息<br />
	 * 消息发送方式：异步发送<br />
	 * 应用：异步发送通常被用于对响应时间敏感的业务场景
	 */
	@Test
	public void asyncProducerTest() {
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
	 * 消息类型：简单消息<br />
	 * 消息发送方式：单向发送<br />
	 * 应用：单向发送用于要求一定可靠性的场景，例如：日志收集。
	 */
	@Test
	public void oneWayProducerTest() throws InterruptedException {
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
