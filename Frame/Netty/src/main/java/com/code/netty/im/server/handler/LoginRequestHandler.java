package com.code.netty.im.server.handler;

import com.code.netty.im.protocol.request.LoginRequestPacket;
import com.code.netty.im.protocol.response.LoginResponsePacket;
import com.code.netty.im.utils.LoginUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * @author 愆凡
 * @date 2021/5/6 22:46
 */
@Slf4j
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket loginRequestPacket) {
		log.debug("新用户登录：" + loginRequestPacket);
		LoginResponsePacket loginResponsePacket = new LoginResponsePacket();

		// 登录校验
		if (valid(loginRequestPacket)) {
			// 校验成功，为 Channel 设置标识
			LoginUtil.markAsLogin(ctx.channel());
			loginResponsePacket.setSuccess(true);

			log.info(new Date() + ": 登录成功!");
		} else {
			// 校验失败
			loginResponsePacket.setSuccess(false);
			loginResponsePacket.setReason("账号密码校验失败");

			log.info(new Date() + ": 登录失败!");
		}

		ctx.channel().writeAndFlush(loginResponsePacket);
	}

	private boolean valid(LoginRequestPacket loginRequestPacket) {
		return true;
	}

}
