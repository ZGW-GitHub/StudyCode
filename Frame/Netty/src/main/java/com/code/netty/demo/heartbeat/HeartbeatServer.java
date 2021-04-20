package com.code.netty.demo.heartbeat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
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
	
	public static final String serverHost = "127.0.0.1";
	public static final Integer serverPort = 7000;

	@Test
	public void test() throws Exception {
		EventLoopGroup bossGroup = new NioEventLoopGroup(1);
		EventLoopGroup workerGroup = new NioEventLoopGroup();

		try {
			ServerBootstrap serverBootstrap = new ServerBootstrap()
					.group(bossGroup, workerGroup)
					.channel(NioServerSocketChannel.class)
					.handler(new LoggingHandler(LogLevel.INFO))
					.childHandler(new ChannelInitializer<SocketChannel>() {
						@Override
						protected void initChannel(SocketChannel ch) {
							ChannelPipeline pipeline = ch.pipeline();
							// 加入一个 Netty 提供 IdleStateHandler
							pipeline.addLast(new IdleStateHandler(7000, 7000, 5, TimeUnit.SECONDS));
							// 加入一个对空闲检测进一步处理的 handler
							pipeline.addLast(new HeartbeatServerHandler());
						}
					});

			// 启动服务器
			ChannelFuture channelFuture = serverBootstrap.bind(serverPort).sync();

			channelFuture.channel().closeFuture().sync();
		} finally {
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}

}
