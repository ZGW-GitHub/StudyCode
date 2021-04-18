package com.code.netty.tcp.demo;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelPipeline;
import io.netty.util.CharsetUtil;

/**
 * 说明：自定义 Handler 需要继承 Netty 规定好的某个 HandlerAdapter(规范)
 *
 * @author 愆凡
 * @date 2021/4/18 20:17
 */
public class NettyServerHandler extends ChannelInboundHandlerAdapter {

	/**
	 * 读取客户端发送的数据
	 *
	 * @param ctx 上下文对象：{@link ChannelHandlerContext} ；含有：管道(pipeline)、通道(channel)、地址
	 * @param msg 客户端发送的数据，默认为：Object
	 */
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) {
		System.err.println("服务器读取线程：" + Thread.currentThread().getName() + "；channle = " + ctx.channel());
		System.err.println("Server ctx = " + ctx);

		System.err.println("看看channel 和 pipeline的关系：");
		Channel channel = ctx.channel();
		ChannelPipeline pipeline = ctx.pipeline(); // 本质是一个双向链接, 出站入站

		// 将 msg 转成一个 ByteBuf（ ByteBuf 是 Netty 提供的，不是 NIO 的 ByteBuffer ）
		ByteBuf buf = (ByteBuf) msg;

		System.err.println("客户端发送消息是:" + buf.toString(CharsetUtil.UTF_8));
		System.err.println("客户端地址:" + channel.remoteAddress());
	}

	/**
	 * 数据读取完毕
	 *
	 * @param ctx 上下文对象
	 */
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) {
		// writeAndFlush 是 write + flush ，作用：将数据写入到缓存，并刷新
		ctx.writeAndFlush(Unpooled.copiedBuffer("hello, 客户端", CharsetUtil.UTF_8));
	}

	/**
	 * 处理异常, 一般是需要关闭通道
	 *
	 * @param ctx 上下文对象
	 * @param cause 异常信息
	 */
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		ctx.close();
	}

}
