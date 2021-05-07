package com.code.netty.im.client.handler;

import com.code.netty.im.protocol.request.LoginRequestPacket;
import com.code.netty.im.protocol.response.LoginResponsePacket;
import com.code.netty.im.utils.LoginUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;
import java.util.UUID;

/**
 * @author 愆凡
 * @date 2021/5/6 22:46
 */
public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {

	/**
	 * 在客户端连接建立成功之后被调用
	 * @param ctx ctx
	 */
	@Override
	public void channelActive(ChannelHandlerContext ctx) {
		System.out.println(new Date() + ": 客户端开始登录");

		// 创建登录对象
		LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
		loginRequestPacket.setUserId(UUID.randomUUID().toString());
		loginRequestPacket.setUsername("test");
		loginRequestPacket.setPassword("pwd");

		// 写数据
		ctx.channel().writeAndFlush(loginRequestPacket);
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, LoginResponsePacket loginResponsePacket) {
		if (loginResponsePacket.isSuccess()) {
			LoginUtil.markAsLogin(ctx.channel());

			System.out.println(new Date() + ": 客户端登录成功");
		} else {
			System.err.println(new Date() + ": 客户端登录失败，原因：" + loginResponsePacket.getReason());
		}
	}

}