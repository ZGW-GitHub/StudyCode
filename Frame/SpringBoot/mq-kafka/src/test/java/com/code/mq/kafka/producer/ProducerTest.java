package com.code.mq.kafka.producer;

import com.code.mq.kafka.MqKafkaApplicationTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.ExecutionException;

/**
 * 三种发送方式：同步发送、异步发送、单向发送
 *
 * @author 愆凡
 * @date 2021/1/12 14:10
 */
public class ProducerTest extends MqKafkaApplicationTest {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@Value("${spring.kafka.template.default-topic}")
	private String topic;

	/**
	 * 发送方式一：同步发送
	 */
	@Test
	public void syncSendTest() throws ExecutionException, InterruptedException {
		// 异步发送消息，通过 get() 阻塞获取发送结果，从而实现同步的效果
		SendResult<String, String> sendResult = kafkaTemplate.send(topic, "hello,kafka...").get();

		System.err.println(sendResult.toString());
	}

	/**
	 * 发送方式二：异步发送
	 */
	@Test
	public void asyncSendTest() throws ExecutionException, InterruptedException {
		// 异步发送消息
		ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, "hello,kafka...");

		// 获取发送结果
		SendResult<String, String> sendResult = future.get();

		System.err.println(sendResult.toString());
	}

	/**
	 * 发送方式三：单向发送
	 */
	@Test
	public void oneWaySendTest() {
		// 需要配置 Producer 的 acks = 0 ，才可以使用这种发送方式。
		// 实际场景下，基本不会使用 oneway 的方式来发送消息，先忽略。
	}

}
