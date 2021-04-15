package com.code.io.net.io.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.Future;

/**
 * @author 愆凡
 */
public class NettyServer {
	public static void main(String[] args) {

		final EventLoopGroup bossGroup = new NioEventLoopGroup(1);
		final EventLoopGroup workerGroup = new NioEventLoopGroup();

		try {

			ServerBootstrap bootstrap = new ServerBootstrap()
					.group(bossGroup, workerGroup)
					.channel(NioServerSocketChannel.class)
					.childHandler(new ChannelInitializer<SocketChannel>() {
						@Override
						protected void initChannel(SocketChannel channel) {
							channel.pipeline().addLast(new ServerChannelHandler());
						}
					});

			// 异步绑定服务器，调用 sync() 方法阻塞等待直到绑定完成
			ChannelFuture channelFuture = bootstrap.bind(28082).sync();

			// 获取 Channel 的 CloseFuture ，并且阻塞当前线程直到 CloseFuture 完成，即：该 Channel 关闭
			channelFuture.channel().closeFuture().sync();

		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			Future<?> bossFuture = bossGroup.shutdownGracefully();
			Future<?> workerFuture = workerGroup.shutdownGracefully();
			bossFuture.syncUninterruptibly();
			workerFuture.syncUninterruptibly();
		}

	}
}
