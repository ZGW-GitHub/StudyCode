package com.code.mq.kafka;

import com.code.mq.kafka.constants.KafkaConsts;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author 愆凡
 * @date 2020/8/31 4:22 下午
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MqKafkaApplicationTest {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@Test
	public void contextLoads() {
	}

	/**
	 * 测试发送消息
	 */
	@Test
	public void testSend() {
		kafkaTemplate.send(KafkaConsts.TOPIC_TEST, "hello,kafka...");
	}


}
