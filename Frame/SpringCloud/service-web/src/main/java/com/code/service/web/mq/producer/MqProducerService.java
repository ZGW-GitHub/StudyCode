package com.code.service.web.mq.producer;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import javax.annotation.Resource;

/**
 * 用来发送消息到 MQ
 *
 * @see EnableBinding
 * @see Source 消息推送管道（生产者）
 *
 * @author 愆凡
 * @date 2021/4/12 22:38
 */
@EnableBinding(Source.class)
public class MqProducerService {

	@Resource
	private MessageChannel output;

	public boolean simpleSend(String messageContent) {
		return output.send(MessageBuilder.withPayload(messageContent).build());
	}

}
