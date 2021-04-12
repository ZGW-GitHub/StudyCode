package com.code.service.user.mq.consumer;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;

/**
 * 用来监听消息
 *
 * @author 愆凡
 * @date 2021/4/12 23:09
 */
@EnableBinding(Sink.class)
public class MqMessageListener {

	@StreamListener(Sink.INPUT)
	public void input(Message<String> message) {
		System.err.println("接收到的 Message ：" + ToStringBuilder.reflectionToString(message));
	}

}
