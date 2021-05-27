package com.code.netty.cc.study.tcp;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

import java.util.concurrent.TimeUnit;

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
	 * @param msg 接收的数据
	 */
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) {
		System.err.println("客户端地址：" + ctx.channel().remoteAddress());
		System.err.println("客户端发送消息是：" + ((ByteBuf) msg).toString(CharsetUtil.UTF_8));

		System.err.println("服务器读取线程：" + Thread.currentThread().getName());
		System.err.println("ChannelHandlerContext：" + ctx);

		System.err.println("Channle：" + ctx.channel());

		// 假设此时有一个非常耗时的业务
		// 方案一：提交到该 Channel 对应的 NioEventLoop 的 taskQueue 中
		ctx.channel().eventLoop().execute(() -> {
			try {
				TimeUnit.SECONDS.sleep(5);

				System.err.println("Channle：" + ctx.channel());
				ctx.writeAndFlush(Unpooled.copiedBuffer("Hello, Client !", CharsetUtil.UTF_8));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		// 方案二：自定义定时任务提交到 scheduleTaskQueue 中
		ctx.channel().eventLoop().schedule(() -> {
			try {
				TimeUnit.SECONDS.sleep(5);

				System.err.println("Channle：" + ctx.channel());
				ctx.writeAndFlush(Unpooled.copiedBuffer("Hello, Client !!", CharsetUtil.UTF_8));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}, 5, TimeUnit.SECONDS);

	}

	/**
	 * 数据读取完毕
	 *
	 * @param ctx 上下文对象
	 */
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) {
		// writeAndFlush 是 write + flush ，作用：将数据写入到缓存，并刷新
		ctx.writeAndFlush(Unpooled.copiedBuffer("Hello, Client !!!", CharsetUtil.UTF_8));
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
