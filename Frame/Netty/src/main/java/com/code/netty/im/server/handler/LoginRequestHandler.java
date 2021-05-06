package com.code.netty.im.server.handler;

import com.code.netty.im.protocol.request.LoginRequestPacket;
import com.code.netty.im.protocol.response.LoginResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;

/**
 * @author 愆凡
 * @date 2021/5/6 22:46
 */
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket loginRequestPacket) {
		System.err.println("新用户登录：" + loginRequestPacket);
		LoginResponsePacket loginResponsePacket = new LoginResponsePacket();

		// 登录校验
		if (valid(loginRequestPacket)) {
			// 校验成功
			loginResponsePacket.setSuccess(true);
			System.err.println(new Date() + ": 登录成功!");
		} else {
			// 校验失败
			loginResponsePacket.setSuccess(false);
			loginResponsePacket.setReason("账号密码校验失败");
			System.err.println(new Date() + ": 登录失败!");
		}

		ctx.channel().writeAndFlush(loginResponsePacket);
	}

	private boolean valid(LoginRequestPacket loginRequestPacket) {
		return true;
	}

}
