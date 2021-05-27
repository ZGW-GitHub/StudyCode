package com.code.netty.nn.rpc.server.handler;

import com.code.netty.nn.rpc.protocol.request.MessageRequestPacket;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 愆凡
 * @date 2021/5/6 22:46
 */
@Slf4j
@Sharable
public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {

	public static final MessageRequestHandler INSTANCE = new MessageRequestHandler();

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket messageRequestPacket) {
		
	}

}
