package com.code.netty.im.server.handler;

import com.code.netty.im.protocol.request.MessageRequestPacket;
import com.code.netty.im.protocol.response.MessageResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * @author 愆凡
 * @date 2021/5/6 22:46
 */
@Slf4j
public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket messageRequestPacket) {
		System.err.println(new Date() + ": 收到客户端消息: " + messageRequestPacket.getMessage());

		MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
		messageResponsePacket.setMessage("服务端回复【 " + messageRequestPacket.getMessage() + " 】");

		ctx.channel().writeAndFlush(messageResponsePacket);
	}

}
