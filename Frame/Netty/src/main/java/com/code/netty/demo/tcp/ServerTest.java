package com.code.netty.demo.tcp;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.junit.Test;

/**
 * @author 愆凡
 * @date 2021/4/18 20:15
 */
public class ServerTest {

	public static final String serverHost = "127.0.0.1";
	public static final Integer serverPort = 7000;

	@Test
	public void test() throws InterruptedException {
		// 创建两个事件循环组：BossGroup 和 WorkerGroup ，它们含有的子线程(NioEventLoop)的个数默认为：cpu核数 * 2
		EventLoopGroup bossGroup = new NioEventLoopGroup(1);
		EventLoopGroup workerGroup = new NioEventLoopGroup();

		try {
			// 创建服务器端的引导对象
			ServerBootstrap bootstrap = new ServerBootstrap();
			// 使用链式编程来配置服务端
			bootstrap.group(bossGroup, workerGroup) // 设置两个线程组
					.channel(NioServerSocketChannel.class) // 使用 NioSocketChannel 作为服务器的通道实现
					.option(ChannelOption.SO_BACKLOG, 128) // 设置线程队列的连接个数
					.childOption(ChannelOption.SO_KEEPALIVE, true) // 设置保持活动连接状态
					// handler 对应 bossGroup , childHandler 对应 workerGroup
					.childHandler(new ChannelInitializer<SocketChannel>() { // 创建一个通道初始化对象(匿名对象)
						// 给 pipeline 设置处理器
						@Override
						protected void initChannel(SocketChannel ch) {
							// 可以使用一个集合管理 SocketChannel，在推送消息时，可以将业务加入到各个 Channel 对应的 NioEventLoop 的 taskQueue 或者 scheduleTaskQueue
							System.out.println("客户端 SocketChannel hashcode = " + ch.hashCode());

							// 给我们的 WorkerGroup 的 EventLoop 对应的管道设置处理器
							ch.pipeline().addLast(new NettyServerHandler());
						}
					});

			System.out.println("Server is ready");

			// 启动服务器(并绑定端口), 生成了一个 ChannelFuture 对象
			ChannelFuture cf = bootstrap.bind(serverPort).sync();

			// 给 ChannelFuture 注册监听器，监控我们关心的事件
			cf.addListener((ChannelFutureListener) future -> {
				if (cf.isSuccess()) {
					System.out.println("监听端口 6668 成功");
				} else {
					System.out.println("监听端口 6668 失败");
				}
			});

			// 对关闭通道进行监听
			cf.channel().closeFuture().sync();
		} finally {
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}

}
