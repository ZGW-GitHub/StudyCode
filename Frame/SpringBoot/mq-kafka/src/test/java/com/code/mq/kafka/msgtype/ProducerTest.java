package com.code.mq.kafka.msgtype;

import com.code.mq.kafka.KafkaApplicationTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;

/**
 * 四种消息类型：顺序消息、定时消息、事务消息
 *
 * @author 愆凡
 * @date 2021/1/14 17:23
 */
public class ProducerTest extends KafkaApplicationTest {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@Value("${spring.kafka.template.default-topic}")
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

	}

	/**
	 * 事务消息
	 */
	@Test
	public void transactionalTest() {

	}

}
