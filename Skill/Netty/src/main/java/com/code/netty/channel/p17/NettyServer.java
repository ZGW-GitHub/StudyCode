package com.code.netty.channel.p17;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class NettyServer {
	public static void main(String[] args) {

		// EventLoopGroup 作用：进行事件的处理，如：接受新连接以及读写数据。
		final EventLoopGroup bossGroup = new NioEventLoopGroup();
		final EventLoopGroup workerGroup = new NioEventLoopGroup();

		try {

			ServerBootstrap bootstrap = new ServerBootstrap()
					.group(bossGroup, workerGroup)
					.channel(NioServerSocketChannel.class)
					// ChannelInitializer 类：当一个新的连接被接收时，一个新的 Channel 就会被创建，ChannelInitializer 将会执行 initChannel() 方法
					// 在本例中，它会将 ServerChannelHandler 的实例添加到该新 Channel 的 ChannelPipeline 中。
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
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}

	}
}
