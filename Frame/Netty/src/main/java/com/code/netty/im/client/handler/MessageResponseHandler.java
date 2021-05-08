package com.code.netty.im.client.handler;

import com.code.netty.im.protocol.response.MessageResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author 愆凡
 * @date 2021/5/6 22:46
 */
public class MessageResponseHandler extends SimpleChannelInboundHandler<MessageResponsePacket> {

	@Override
	protected void channelRead0(ChannelHandlerContext channelHandlerContext, MessageResponsePacket messageResponsePacket) {
		String fromUserid = messageResponsePacket.getFromUserid();
		String fromUserName = messageResponsePacket.getFromUserName();

		System.err.println("收到[" + fromUserid + "_" + fromUserName + "]发来的消息: " + messageResponsePacket.getMessage());
	}

}
