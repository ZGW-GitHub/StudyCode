package com.code.service.web.controller;

import com.code.service.web.mq.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author 愆凡
 * @date 2021/4/11 15:00
 */
@RestController
@RequestMapping("/message")
public class MessageController {

	@Autowired
	private MessageService messageService;

	@RequestMapping("/simple")
	public String simpleSend() {
		String messageContent = UUID.randomUUID().toString();

		boolean send = messageService.simpleSend(messageContent);

		return "发送消息：" + messageContent + "，result：" + send;
	}

}
