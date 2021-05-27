package com.code.netty.ee.im.client.handler;

import com.code.netty.ee.im.protocol.response.LoginResponsePacket;
import com.code.netty.ee.im.server.session.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 愆凡
 * @date 2021/5/6 22:46
 */
@Slf4j
public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, LoginResponsePacket loginResponsePacket) {
		if (loginResponsePacket.isSuccess()) {
			System.err.println("客户端登录成功，分配的 userid ：" + loginResponsePacket.getUserid());

			SessionUtil.clientLoginFlag(ctx.channel(), loginResponsePacket);
		} else {
			log.warn("客户端登录失败，原因：" + loginResponsePacket.getReason());
		}
	}

}
