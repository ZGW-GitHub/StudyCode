package com.code.mq.rocketmq.producer;

import com.code.mq.rocketmq.RocketMqApplicationTest;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

/**
 * 四种消息类型：广播消息、顺序消息、定时消息、事务消息
 *
 * @author 愆凡
 * @date 2021/1/14 17:23
 */
public class MsgTypeTest extends RocketMqApplicationTest {

	@Autowired
	private RocketMQTemplate rocketMQTemplate;

	@Value("${rocketmq.producer.customized-trace-topic}")
	private String topic;

	/**
	 * 广播消息
	 */
	public void broadcastingTest() {

	}

	/**
	 * 顺序消息
	 */
	public void orderTest() {

	}

	/**
	 * 定时消息
	 */
	public void timeTest() {

	}

	/**
	 * 事务消息
	 */
	public void transactionalTest() {

	}

}
