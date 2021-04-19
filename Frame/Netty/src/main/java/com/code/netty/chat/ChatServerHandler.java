package com.code.netty.chat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.text.SimpleDateFormat;

/**
 * @author 愆凡
 * @date 2021/4/19 17:23
 */
public class ChatServerHandler extends SimpleChannelInboundHandler<String> {
	// 定义一个 channle 组，管理所有的 channel 。GlobalEventExecutor.INSTANCE 是全局的事件执行器，是一个单例
	private static final ChannelGroup CHANNEL_GROUP = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * 表示 channel 连接上了，提示 xx 加入聊天
	 */
	@Override
	public void handlerAdded(ChannelHandlerContext ctx) {
		Channel channel = ctx.channel();

		// 将该客户加入聊天的信息推送给其它在线的客户端
		// ChannelGroup 会遍历其中所有的 Channel 并执行 writeAndFlush 方法，而不需要我们自己遍历
		CHANNEL_GROUP.writeAndFlush(sdf.format(new java.util.Date()) + " [ 客户端 - " + channel.remoteAddress() + " ]：加入聊天 \n");
		CHANNEL_GROUP.add(channel);
	}

	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) {
		Channel channel = ctx.channel();
		CHANNEL_GROUP.writeAndFlush("[客户端]" + channel.remoteAddress() + " 离开了\n");
		CHANNEL_GROUP.writeAndFlush(sdf.format(new java.util.Date()) + " [ 客户端 - " + channel.remoteAddress() + " ]：离开了 \n");
		System.out.println("channelGroup size" + CHANNEL_GROUP.size());

	}

	/**
	 * 表示 channel 处于活动状态, 提示 xx 上线
	 */
	@Override
	public void channelActive(ChannelHandlerContext ctx) {

		System.out.println(ctx.channel().remoteAddress() + " 上线了~");
	}

	//表示channel 处于不活动状态, 提示 xx离线了
	@Override
	public void channelInactive(ChannelHandlerContext ctx) {

		System.out.println(ctx.channel().remoteAddress() + " 离线了~");
	}

	//读取数据
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, String msg) {

		//获取到当前channel
		Channel channel = ctx.channel();
		//这时我们遍历channelGroup, 根据不同的情况，回送不同的消息

		CHANNEL_GROUP.forEach(ch -> {
			if (channel != ch) { //不是当前的channel,转发消息
				ch.writeAndFlush("[客户]" + channel.remoteAddress() + " 发送了消息" + msg + "\n");
			} else {//回显自己发送的消息给自己
				ch.writeAndFlush("[自己]发送了消息" + msg + "\n");
			}
		});
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		//关闭通道
		ctx.close();
	}
}
