package com.code.mq.kafka.test;

import com.code.mq.kafka.MqKafkaApplicationTest;
import com.code.mq.kafka.constants.KafkaConsts;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.ExecutionException;

/**
 * @author 愆凡
 * @date 2020/9/1 11:55 上午
 */
public class KafkaTest extends MqKafkaApplicationTest {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	/**
	 * 测试发送消息
	 */
	@Test
	public void testSend() {
		try {
			ListenableFuture<SendResult<String, String>> future =
					kafkaTemplate.send(KafkaConsts.TOPIC_TEST, "hello,kafka...");

			SendResult<String, String> sendResult = future.get();

			System.out.println(sendResult.toString());
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}

}
