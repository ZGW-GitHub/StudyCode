package com.code.netty.ee.im.client.handler;

import com.code.netty.ee.im.protocol.response.JoinGroupResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 愆凡
 * @date 2021/5/11 09:43
 */
@Slf4j
public class JoinGroupResponseHandler extends SimpleChannelInboundHandler<JoinGroupResponsePacket> {

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, JoinGroupResponsePacket msg) throws Exception {
		if (msg.isSuccess()) {
			System.err.println("加入群聊成功，成员：" + String.join("、", msg.getUserNameList()));
		} else {
			log.warn("加入群聊失败，原因：" + msg.getReason());
		}
	}

}
