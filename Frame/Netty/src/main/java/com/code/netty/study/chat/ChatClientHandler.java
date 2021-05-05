package com.code.netty.study.chat;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author 愆凡
 * @date 2021/4/19 17:26
 */
public class ChatClientHandler extends SimpleChannelInboundHandler<String> {

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, String msg) {
		System.err.println(msg.trim());
	}

}
