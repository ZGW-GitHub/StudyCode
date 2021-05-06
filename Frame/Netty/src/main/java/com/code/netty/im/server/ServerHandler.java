package com.code.netty.im.server;

import com.code.netty.im.protocol.Packet;
import com.code.netty.im.protocol.PacketCodec;
import com.code.netty.im.protocol.request.LoginRequestPacket;
import com.code.netty.im.protocol.request.MessageRequestPacket;
import com.code.netty.im.protocol.response.LoginResponsePacket;
import com.code.netty.im.protocol.response.MessageResponsePacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.Date;

/**
 * @author 愆凡
 * @date 2021/4/19 17:23
 */
public class ServerHandler extends SimpleChannelInboundHandler<Object> {
	// 定义一个 channle 组，管理所有的 channel 。GlobalEventExecutor.INSTANCE 是全局的事件执行器，是一个单例
	// 必须为 static 的，因为 Server 那里：pipeline.addLast(new ChatServerHandler());
	private static final ChannelGroup CHANNEL_GROUP = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Object msg) {
		// 解码
		Packet packet = PacketCodec.decode((ByteBuf) msg);

		// 判断是否是登录请求数据包
		if (packet instanceof LoginRequestPacket) {
			System.err.println("新用户登录：" + packet);
			LoginResponsePacket loginResponsePacket = new LoginResponsePacket();

			// 登录校验
			if (valid((LoginRequestPacket) packet)) {
				// 校验成功
				loginResponsePacket.setSuccess(true);
			} else {
				// 校验失败
				loginResponsePacket.setSuccess(false);
				loginResponsePacket.setReason("账号密码校验失败");
			}

			ByteBuf responseByteBuf = PacketCodec.encode(ctx.alloc(), loginResponsePacket);
			ctx.channel().writeAndFlush(responseByteBuf);
		}

		// 判断是否是消息请求数据包
		if (packet instanceof MessageRequestPacket) {
			MessageRequestPacket messageRequestPacket = (MessageRequestPacket) packet;
			System.err.println(new Date() + ": 收到客户端消息: " + messageRequestPacket.getMessage());

			MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
			messageResponsePacket.setMessage("服务端回复【" + messageRequestPacket.getMessage() + "】");

			ByteBuf responseByteBuf = PacketCodec.encode(ctx.alloc(), messageResponsePacket);
			ctx.channel().writeAndFlush(responseByteBuf);
		}
	}

	private boolean valid(LoginRequestPacket loginRequestPacket) {
		return true;
	}

}
