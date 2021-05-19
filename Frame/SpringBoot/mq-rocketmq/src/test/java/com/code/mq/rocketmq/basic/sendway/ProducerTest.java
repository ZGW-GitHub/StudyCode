package com.code.mq.rocketmq.basic.sendway;

import com.code.mq.rocketmq.RocketMqApplicationTest;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

/**
 * 三种发送方式：同步发送、异步发送、单向发送
 *
 * @author 愆凡
 * @date 2020/12/31 11:36
 */
public class ProducerTest extends RocketMqApplicationTest {

	@Autowired
	private RocketMQTemplate rocketMQTemplate;

	@Value("${rocketmq.producer.customized-trace-topic}")
	private String topic;

	/**
	 * 同步发送
	 */
	@Test
	public void syncSendTest() {
		try {
			SendResult sendResult = rocketMQTemplate.syncSend(topic, "Sync 消息");
			System.err.println("sendResult : " + sendResult);
		} catch (Exception e) {
			// TODO 发送消息出现异常，重新发送
		}
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
				
				// TODO 发送消息出现异常，重新发送
			}
		}, 3_000);
	}

	/**
	 * 单向发送
	 */
	@Test
	public void oneWaySendTest() throws InterruptedException {
		rocketMQTemplate.sendOneWay(topic, "OneWay 消息");

		Thread.sleep(3_000);
	}

}
