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
 * @author 愆凡
 * @date 2021/1/12 14:10
 */
public class ProducerTest extends MqKafkaApplicationTest {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@Value("${spring.kafka.template.default-topic}")
	private String topic;

	@Test
	public void testSend() throws ExecutionException, InterruptedException {
		// 发送消息
		ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, "hello,kafka...");

		SendResult<String, String> sendResult = future.get();

		System.err.println(sendResult.toString());
	}

}
