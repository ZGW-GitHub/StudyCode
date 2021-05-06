package com.code.netty.im.client.handler;

import com.code.netty.im.protocol.response.MessageResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;

/**
 * @author 愆凡
 * @date 2021/5/6 22:46
 */
public class MessageResponseHandler extends SimpleChannelInboundHandler<MessageResponsePacket> {

	@Override
	protected void channelRead0(ChannelHandlerContext channelHandlerContext, MessageResponsePacket messageResponsePacket) {
		System.out.println(new Date() + ": 收到服务端的消息: " + messageResponsePacket.getMessage());
	}

}
