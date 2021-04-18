package com.code.netty.tcp.demo;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.junit.Test;

/**
 * @author 愆凡
 * @date 2021/4/18 20:35
 */
public class ClientTest {

	@Test
	public void test() throws InterruptedException {
		// 客户端需要一个事件循环组
		EventLoopGroup group = new NioEventLoopGroup();

		try {
			// 创建客户端的引导对象
			Bootstrap bootstrap = new Bootstrap();
			// 设置相关参数
			bootstrap.group(group) // 设置线程组
					.channel(NioSocketChannel.class) // 使用 NioSocketChannel 作为客户端的通道实现
					.handler(new ChannelInitializer<SocketChannel>() {
						@Override
						protected void initChannel(SocketChannel ch) {
							ch.pipeline().addLast(new NettyClientHandler()); // 为管道设置处理器
						}
					});

			System.out.println("Client ok");
			// 启动客户端去连接服务器端
			ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 6668).sync();

			// 对关闭通道进行监听
			channelFuture.channel().closeFuture().sync();
		} finally {
			group.shutdownGracefully();
		}
	}

}
