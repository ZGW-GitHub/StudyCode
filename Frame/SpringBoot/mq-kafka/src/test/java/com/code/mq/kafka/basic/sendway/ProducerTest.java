package com.code.mq.kafka.basic.sendway;

import com.code.mq.kafka.KafkaApplicationTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import javax.annotation.Nonnull;
import java.util.concurrent.ExecutionException;

/**
 * 三种发送方式：同步发送、异步发送、单向发送
 *
 * @author 愆凡
 * @date 2021/1/12 14:10
 */
@Slf4j
public class ProducerTest extends KafkaApplicationTest {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@Value("${spring.kafka.template.default-topic}")
	private String topic;

	/**
	 * 同步发送
	 */
	@Test
	public void syncSendTest() {
		try {
			// 异步发送消息，通过 get() 阻塞获取发送结果，从而实现同步的效果
			SendResult<String, String> sendResult = kafkaTemplate.send(topic, "Async 消息").get();

			System.err.println(sendResult.toString());
		} catch (ExecutionException | InterruptedException e) {
			log.error("发送失败，e ：", e);

			// TODO 发送消息出现异常，重新发送
		}
	}

	/**
	 * 异步发送
	 */
	@Test
	public void asyncSendTest() {
		// 异步发送消息
		ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, "Async 消息");

		// 获取发送结果
		future.addCallback(new ListenableFutureCallback<>() {
			@Override
			public void onSuccess(SendResult<String, String> result) {
				System.err.println(result.toString());
			}

			@Override
			public void onFailure(@Nonnull Throwable e) {
				log.error("发送失败，e ：", e);

				// TODO 发送消息出现异常，重新发送
			}
		});
	}

	/**
	 * 单向发送
	 */
	@Test
	public void oneWaySendTest() {
		// 需要配置 Producer 的 acks = 0 ，才可以使用这种发送方式。
		// 实际场景下，基本不会使用 oneway 的方式来发送消息，先忽略。
	}

}
