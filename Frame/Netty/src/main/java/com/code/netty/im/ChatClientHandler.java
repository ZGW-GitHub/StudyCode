package com.code.netty.im;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * @author 愆凡
 * @date 2021/4/19 17:26
 */
public class ChatClientHandler extends SimpleChannelInboundHandler<String> {

	/**
	 * 在客户端连接建立成功之后被调用
	 * @param ctx ctx
	 */
	@Override
	public void channelActive(ChannelHandlerContext ctx) {
		System.out.println(new Date() + ": 客户端写出数据");

		// 1. 获取数据
		ByteBuf buffer = getByteBuf(ctx);

		// 2. 写数据
		ctx.channel().writeAndFlush(buffer);
	}

	private ByteBuf getByteBuf(ChannelHandlerContext ctx) {
		// 1. 获取二进制抽象 ByteBuf
		ByteBuf buffer = ctx.alloc().buffer();

		// 2. 准备数据，指定字符串的字符集为 utf-8
		byte[] bytes = "你好，闪电侠!".getBytes(StandardCharsets.UTF_8);

		// 3. 填充数据到 ByteBuf
		buffer.writeBytes(bytes);

		return buffer;
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, String msg) {
		System.err.println(msg.trim());
	}

}
