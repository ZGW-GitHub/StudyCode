package com.code.mq.rocketmq.sentinel;

import com.code.mq.rocketmq.RocketMqApplicationTest;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

/**
 * 使用阿里 Sentinel 进行消费者限流
 *
 * @author 愆凡
 * @date 2021/3/10 09:18
 */
public class SentinelTest extends RocketMqApplicationTest {

	@Autowired
	private RocketMQTemplate rocketMQTemplate;

	@Value("${rocketmq.producer.customized-trace-topic}")
	private String topic;

	@Value("${rocketmq.producer.group}")
	private String group;

	@Test
	public void producerTest() {

	}

	@Test
	public void consumerTest() {

	}

}
