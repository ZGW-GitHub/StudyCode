package com.code.netty.im.server.handler;

import cn.hutool.core.util.StrUtil;
import com.code.netty.im.protocol.request.LoginRequestPacket;
import com.code.netty.im.protocol.response.LoginResponsePacket;
import com.code.netty.im.server.session.Session;
import com.code.netty.im.server.session.SessionUtil;
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
		if (validPassword(loginRequestPacket)) {
			log.info("[" + loginRequestPacket.getUsername() + "]登录成功!");

			// 为用户创建会话信息
			Session session = new Session(genderUserid(), loginRequestPacket.getUsername());

			// 将会话信息与 Channel 绑定
			SessionUtil.bindSession(session, ctx.channel());

			loginResponsePacket.setSuccess(true);
			loginResponsePacket.setUserid(session.getUserid());
		} else {
			log.info(new Date() + ": 登录失败!");

			loginResponsePacket.setSuccess(false);
			loginResponsePacket.setReason("账号密码校验失败");
		}

		ctx.channel().writeAndFlush(loginResponsePacket);
	}

	private boolean validPassword(LoginRequestPacket loginRequestPacket) {
		return true;
	}

	private String genderUserid() {
		String userid = StrUtil.uuid().substring(32);
		if (SessionUtil.USER_ID_CHANNEL_MAP.containsKey(userid)) {
			return genderUserid();
		}
		return userid;
	}

	/**
	 * 用户断线，取消 Session 与 Channel 的绑定
	 * 
	 * @param ctx ctx
	 */
	@Override
	public void channelInactive(ChannelHandlerContext ctx) {
		SessionUtil.unBindSession(ctx.channel());
	}
}
