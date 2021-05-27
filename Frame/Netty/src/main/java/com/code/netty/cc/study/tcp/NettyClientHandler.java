package com.code.netty.cc.study.tcp;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * @author 愆凡
 * @date 2021/4/18 20:42
 */
public class NettyClientHandler extends ChannelInboundHandlerAdapter {

	/**
	 * 当通道就绪就会触发该方法
	 *
	 * @param ctx 上下文对象
	 */
	@Override
	public void channelActive(ChannelHandlerContext ctx) {
		System.err.println("ChannelHandlerContext = " + ctx);
		ctx.writeAndFlush(Unpooled.copiedBuffer("Hello, Server !!!", CharsetUtil.UTF_8));
	}

	/**
	 * 当通道有读取事件时，会触发
	 *
	 * @param ctx 上下文对象
	 * @param msg 接收的数据
	 */
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) {
		System.err.println("服务器的地址：" + ctx.channel().remoteAddress());
		System.err.println("服务器回复的消息：" + ((ByteBuf) msg).toString(CharsetUtil.UTF_8));
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		ctx.close();
	}

}
