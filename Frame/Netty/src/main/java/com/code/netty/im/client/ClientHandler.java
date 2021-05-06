package com.code.netty.im.client;

import com.code.netty.im.protocol.Packet;
import com.code.netty.im.protocol.PacketCodec;
import com.code.netty.im.protocol.request.LoginRequestPacket;
import com.code.netty.im.protocol.response.LoginResponsePacket;
import com.code.netty.im.protocol.response.MessageResponsePacket;
import com.code.netty.im.utils.LoginUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;
import java.util.UUID;

/**
 * @author 愆凡
 * @date 2021/4/19 17:26
 */
public class ClientHandler extends SimpleChannelInboundHandler<Object> {

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

		// 编码
		ByteBuf buffer = PacketCodec.encode(ctx.alloc(), loginRequestPacket);

		// 写数据
		ctx.channel().writeAndFlush(buffer);
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Object msg) {
		ByteBuf responseByteBuf = (ByteBuf) msg;

		Packet packet = PacketCodec.decode(responseByteBuf);

		if (packet instanceof LoginResponsePacket) {
			LoginResponsePacket loginResponsePacket = (LoginResponsePacket) packet;

			if (loginResponsePacket.isSuccess()) {
				LoginUtil.markAsLogin(ctx.channel());
				System.out.println(new Date() + ": 客户端登录成功");
			} else {
				System.err.println(new Date() + ": 客户端登录失败，原因：" + loginResponsePacket.getReason());
			}
		}

		if (packet instanceof MessageResponsePacket) {
			MessageResponsePacket messageResponsePacket = (MessageResponsePacket) packet;

			System.out.println(new Date() + ": 收到服务端的消息: " + messageResponsePacket.getMessage());
		}
	}
}
