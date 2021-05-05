package com.code.netty.im;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import lombok.Setter;
import org.junit.Test;

/**
 * Netty 群聊系统案例
 *
 * @author 愆凡
 * @date 2021/4/19 17:19
 */
@Setter
@SuppressWarnings("all")
public class ChatServer {

	@Test
	public void test() throws Exception {
		ChatServer server = new ChatServer();
		server.setPort(SERVER_PORT);
		server.run();
	}

	public static final String SERVER_HOST = "127.0.0.1";
	public static final Integer SERVER_PORT = 7000;

	private int port; // 监听端口

	/**
	 * 编写 run 方法，处理客户端的请求
	 *
	 * @throws Exception e
	 */
	public void run() throws Exception {
		EventLoopGroup bossGroup = new NioEventLoopGroup(1);
		EventLoopGroup workerGroup = new NioEventLoopGroup(); // cpu核心数*2

		ServerBootstrap bootstrap = new ServerBootstrap()
				.group(bossGroup, workerGroup)
				.channel(NioServerSocketChannel.class)
				.option(ChannelOption.SO_BACKLOG, 128)
				.childOption(ChannelOption.SO_KEEPALIVE, true)
				.childHandler(new ChannelInitializer<SocketChannel>() {
					@Override
					protected void initChannel(SocketChannel ch) {
						ChannelPipeline pipeline = ch.pipeline();
						pipeline.addLast("decoder", new StringDecoder());
						pipeline.addLast("encoder", new StringEncoder());
						// 添加业务处理 handler
						pipeline.addLast(new ChatServerHandler());
					}
				});

		System.out.println("Netty Chat Server 启动");
		ChannelFuture channelFuture = bootstrap.bind(port).sync();

		// 对关闭通道进行监听
		channelFuture.channel().closeFuture().addListener((ChannelFutureListener) cf -> {
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		});

		Thread.currentThread().join();
	}

}
