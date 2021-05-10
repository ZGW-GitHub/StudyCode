package com.code.netty.im.client.handler;

import com.code.netty.im.protocol.response.CreateGroupResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 愆凡
 * @date 2021/5/10 16:03
 */
@Slf4j
public class CreateGroupResponseHandler extends SimpleChannelInboundHandler<CreateGroupResponsePacket> {
	
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, CreateGroupResponsePacket msg) {
		if (msg.isSuccess()) {
			System.err.println("群聊创建成功，群聊ID：" + msg.getGroupid() + "，成员：" + String.join("、", msg.getUserNameList()));
		} else {
			log.warn("群聊创建失败，原因：" + msg.getReason());
		} 
	}
	
}
