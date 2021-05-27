package com.code.netty.cc.study.websocket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import org.junit.Test;

/**
 * 示例：通过 WebSocket 编程实现服务器和客户端长连接
 *
 * @author 愆凡
 * @date 2021/4/20 11:51
 */
@SuppressWarnings("all")
public class WebSocketServer {

	@Test
	public void test() throws InterruptedException {
		EventLoopGroup bossGroup = new NioEventLoopGroup(1);
		EventLoopGroup workerGroup = new NioEventLoopGroup();

		ServerBootstrap serverBootstrap = new ServerBootstrap()
				.group(bossGroup, workerGroup)
				.channel(NioServerSocketChannel.class)
				.handler(new LoggingHandler(LogLevel.INFO))
				.childHandler(new ChannelInitializer<SocketChannel>() {
					@Override
					protected void initChannel(SocketChannel ch) throws Exception {
						ChannelPipeline pipeline = ch.pipeline();
						// 因为基于 http 协议，使用 http 的编码和解码器
						pipeline.addLast(new HttpServerCodec());
						// 是以块方式写，添加 ChunkedWriteHandler 处理器
						pipeline.addLast(new ChunkedWriteHandler());
						/**
						 * 说明
						 * 1. http 数据在传输过程中是分段的（这就是为什么，当浏览器发送大量数据时，就会发出多次 http 请求）
						 * 2. HttpObjectAggregator ，可以将多个段聚合
						 */
						pipeline.addLast(new HttpObjectAggregator(8192));
						/**
						 * 说明
						 * 1. 对应 websocket ，它的数据是以 帧(frame) 形式传递的
						 * 2. WebSocketServerProtocolHandler 核心功能是将 http协议升级为 ws 协议，保持长连接
						 */
						pipeline.addLast(new WebSocketServerProtocolHandler("/hello"));
						pipeline.addLast(new WebSocketTextFrameHandler());
					}
				});

		// 启动服务器
		ChannelFuture channelFuture = serverBootstrap.bind(7000).sync();

		// 对关闭通道进行监听
		channelFuture.channel().closeFuture().addListener((ChannelFutureListener) cf -> {
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		});

		Thread.currentThread().join();
	}

}
