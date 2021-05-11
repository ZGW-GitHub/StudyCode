package com.code.netty.study.heartbeat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * Netty 心跳检测机制案例<br/><br/>
 *
 * Netty 提供的处理空闲状态的处理器：{@link IdleStateHandler}<br/>
 * 1. long readerIdleTime : 表示多长时间没有读，就会发送一个心跳检测包检测是否连接<br/>
 * 2. long writerIdleTime : 表示多长时间没有写，就会发送一个心跳检测包检测是否连接<br/>
 * 3. long allIdleTime : 表示多长时间没有读写，就会发送一个心跳检测包检测是否连接<br/>
 * 4. 当 IdleStateEvent 触发后，就会传递给管道的下一个 handler 去处理，通过下一个 handler 的 userEventTiggered 方法去处理 IdleStateEvent(读空闲，写空闲，读写空闲)<br/><br/>
 * @author 愆凡
 * @date 2021/4/20 11:16
 */
@SuppressWarnings("all")
public class HeartbeatServer {

	public static final String SERVER_HOST = "127.0.0.1";
	public static final Integer SERVER_PORT = 7000;

	@Test
	public void test() throws Exception {
		EventLoopGroup bossGroup = new NioEventLoopGroup(1);
		EventLoopGroup workerGroup = new NioEventLoopGroup();

		ServerBootstrap serverBootstrap = new ServerBootstrap()
				.group(bossGroup, workerGroup)
				.channel(NioServerSocketChannel.class)
				.handler(new LoggingHandler(LogLevel.INFO))
				.childHandler(new ChannelInitializer<SocketChannel>() {
					@Override
					protected void initChannel(SocketChannel ch) {
						ChannelPipeline pipeline = ch.pipeline();
						// 加入一个 Netty 提供 IdleStateHandler ，有 4 个参数（参数值为 0 表示不检测）：
						// 第一个表示读空闲时间，指的是在这段时间内如果没有数据读到，就表示连接假死；
						// 第二个是写空闲时间，指的是在这段时间如果没有写数据，就表示连接假死；
						// 第三个参数是读写空闲时间，表示在这段时间内如果没有产生数据读或者写，就表示连接假死。
						pipeline.addLast(new IdleStateHandler(7000, 7000, 5, TimeUnit.SECONDS));
						// 加入一个对空闲检测进一步处理的 handler
						pipeline.addLast(new HeartbeatServerHandler());
					}
				});

		// 启动服务器
		ChannelFuture channelFuture = serverBootstrap.bind(SERVER_PORT).sync();

		// 对关闭通道进行监听
		channelFuture.channel().closeFuture().addListener((ChannelFutureListener) cf -> {
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		});

		Thread.currentThread().join();
	}

}
