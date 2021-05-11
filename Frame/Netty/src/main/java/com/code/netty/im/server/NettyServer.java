package com.code.netty.im.server;

import com.code.netty.im.codec.PacketCodecHandler;
import com.code.netty.im.codec.Spliter;
import com.code.netty.im.server.handler.*;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.net.InetSocketAddress;

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
						// 添加业务处理 handler
						pipeline.addLast(new Spliter()); // 不能共享，因为它内部实现是与每个 channel 有关，每个 Spliter 需要维持每个 channel 当前读到的数据，也就是说它是有状态的。
						pipeline.addLast(PacketCodecHandler.INSTANCE);
						pipeline.addLast(LoginRequestHandler.INSTANCE);
						pipeline.addLast(AuthHandler.INSTANCE);
						pipeline.addLast(MessageSpliterHandler.INSTANCE);
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
