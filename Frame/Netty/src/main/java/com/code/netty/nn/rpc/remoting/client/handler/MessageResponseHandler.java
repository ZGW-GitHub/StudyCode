package com.code.netty.nn.rpc.remoting.client.handler;

import com.code.netty.nn.rpc.remoting.protocol.response.MessageResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author 愆凡
 * @date 2021/5/6 22:46
 */
public class MessageResponseHandler extends SimpleChannelInboundHandler<MessageResponsePacket> {

	@Override
	protected void channelRead0(ChannelHandlerContext channelHandlerContext, MessageResponsePacket messageResponsePacket) {

	}

}
