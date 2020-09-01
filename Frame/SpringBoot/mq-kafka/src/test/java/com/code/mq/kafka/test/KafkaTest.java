package com.code.mq.kafka.test;

import com.code.mq.kafka.MqKafkaApplicationTest;
import com.code.mq.kafka.constants.KafkaConsts;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

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
		kafkaTemplate.send(KafkaConsts.TOPIC_TEST, "hello,kafka...");
	}

}
