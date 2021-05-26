package com.code.netty.im.server;

import com.code.netty.im.codec.PacketCodecHandler;
import com.code.netty.im.codec.Spliter;
import com.code.netty.im.server.handler.AuthHandler;
import com.code.netty.im.server.handler.HeartBeatRequestHandler;
import com.code.netty.im.server.handler.LoginRequestHandler;
import com.code.netty.im.server.handler.RequestSpliterHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;

/**
 * Netty 群聊系统案例
 *
 * @author 愆凡
 * @date 2021/4/19 17:19
 */
@Slf4j
@Setter
@SuppressWarnings("all")
public class NettyServer {

	@Test
	public void test() throws Exception {
		new NettyServer().run();
	}

	public static final String SERVER_HOST = "127.0.0.1";
	public static final Integer SERVER_PORT = 7000;

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
				.localAddress(new InetSocketAddress(SERVER_PORT))
				.channel(NioServerSocketChannel.class)
				.option(ChannelOption.SO_BACKLOG, 128)
				.childOption(ChannelOption.SO_KEEPALIVE, true)
				.childHandler(new ChannelInitializer<SocketChannel>() {
					@Override
					protected void initChannel(SocketChannel ch) {
						ChannelPipeline pipeline = ch.pipeline();
						// 加入一个 Netty 提供 IdleStateHandler ，有 4 个参数（参数值为 0 表示不检测）：
						// 第一个表示读空闲时间，指的是在这段时间内如果没有数据读到，就表示连接假死；
						// 第二个是写空闲时间，指的是在这段时间如果没有写数据，就表示连接假死；
						// 第三个参数是读写空闲时间，表示在这段时间内如果没有产生数据读或者写，就表示连接假死。
						pipeline.addLast(new IdleStateHandler(10, 10, 10, TimeUnit.SECONDS)); // 不能共享，因为每个 Channel 维护的有自己上次的读写时间
						pipeline.addLast(new Spliter()); // 不能共享，因为它内部实现是与每个 channel 有关，每个 Spliter 需要维持每个 channel 当前读到的数据，也就是说它是有状态的。
						pipeline.addLast(PacketCodecHandler.INSTANCE);
						pipeline.addLast(LoginRequestHandler.INSTANCE);
						pipeline.addLast(HeartBeatRequestHandler.INSTANCE);
						pipeline.addLast(AuthHandler.INSTANCE);
						pipeline.addLast(RequestSpliterHandler.INSTANCE);
					}
				});

		ChannelFuture channelFuture = bootstrap.bind().sync();

		// 对关闭通道进行监听
		channelFuture.channel().closeFuture().addListener((ChannelFutureListener) cf -> {
			log.info("Netty Server 关闭");

			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		});

		log.info("Netty Server 启动");
		Thread.currentThread().join();
	}

}
