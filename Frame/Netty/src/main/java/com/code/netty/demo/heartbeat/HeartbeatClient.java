package com.code.netty.demo.heartbeat;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import lombok.Setter;
import org.junit.Test;

/**
 * Netty 群聊系统案例
 * 
 * @author 愆凡
 * @date 2021/4/19 17:24
 */
@Setter
@SuppressWarnings("all")
public class HeartbeatClient {

	@Test
	public void test() throws Exception {
		EventLoopGroup group = new NioEventLoopGroup();

		try {
			Bootstrap bootstrap = new Bootstrap()
					.group(group)
					.channel(NioSocketChannel.class)
					.handler(new ChannelInitializer<SocketChannel>() {
						@Override
						protected void initChannel(SocketChannel ch) throws Exception {
							ChannelPipeline pipeline = ch.pipeline();
							pipeline.addLast("decoder", new StringDecoder());
							pipeline.addLast("encoder", new StringEncoder());
						}
					});

			ChannelFuture channelFuture = bootstrap.connect(HeartbeatServer.SERVER_HOST, HeartbeatServer.SERVER_PORT).sync();

			Channel channel = channelFuture.channel();
			System.out.println("Netty Chat Client 启动：" + channel.localAddress());

			// 对关闭通道进行监听
			channelFuture.channel().closeFuture().sync();
		} finally {
			group.shutdownGracefully();
		}
	}

}
