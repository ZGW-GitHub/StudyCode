package com.code.mq.rocketmq.producer;

import com.code.mq.rocketmq.RocketMqApplicationTest;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.io.UnsupportedEncodingException;

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
	public void oneWayProducerTest() throws MQClientException, UnsupportedEncodingException, RemotingException, InterruptedException {
		// 构建生产者实例
		DefaultMQProducer producer = new DefaultMQProducer("group_name_sync");
		// 设置发送消息失败重试次数
		producer.setRetryTimesWhenSendFailed(3);
		// 设置NameServer地址
		producer.setNamesrvAddr("192.168.56.101:9876;192.168.56.102:9876");
		// 启动生产者
		producer.start();

		for (int i = 0; i < 10; i++) {

			// 创建一个消息实例，指定主题、Tag、消息主题
			Message message = new Message(
					"TopicTest",
					"TagA",
					("Hello RocketMQ !" + i).getBytes(RemotingHelper.DEFAULT_CHARSET)
			);

			// 单向发送消息
			// 由于在OneWay方式发送消息时没有请求应答处理，一旦出现消息发送失败，则会因为没有重试而导致数据丢失。
			// 若数据不可丢，建议选用可靠同步或可靠异步发送方式。
			producer.sendOneway(message);

		}

		// 关闭生产者
		producer.shutdown();
	}

	@Test
	public void timeMessageTest() throws MQClientException, UnsupportedEncodingException, RemotingException, InterruptedException, MQBrokerException {
		// 构建生产者实例
		DefaultMQProducer producer = new DefaultMQProducer("group_name_sync");
		// 设置发送消息失败重试次数
		producer.setRetryTimesWhenSendFailed(3);
		// 设置NameServer地址
		producer.setNamesrvAddr("192.168.56.101:9876;192.168.56.102:9876");
		// 启动生产者
		producer.start();

		for (int i = 0; i < 10; i++) {

			// 创建一个消息实例，指定主题、Tag、消息主题
			Message message = new Message(
					"TopicTest",
					"TagA",
					("Hello RocketMQ !" + i).getBytes(RemotingHelper.DEFAULT_CHARSET)
			);

			// 预设值的时间长度（1s/5s/10s/30s/1m/2m/3m/4m/5m/6m/7m/8m/9m/10m/20m/30m/1h/2h）
			// 此消息将在10秒后传递给消费者。
			message.setDelayTimeLevel(3);
			producer.send(message);

		}

		// 关闭生产者
		producer.shutdown();
	}

}
