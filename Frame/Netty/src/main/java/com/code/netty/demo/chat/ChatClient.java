package com.code.netty.demo.chat;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import lombok.Setter;
import org.junit.Test;

import java.util.Scanner;

/**
 * Netty 群聊系统案例
 *
 * @author 愆凡
 * @date 2021/4/19 17:24
 */
@Setter
@SuppressWarnings("all")
public class ChatClient {

	@Test
	public void clientOne() throws Exception {
		ChatClient client = new ChatClient();
		client.setServerHost(ChatServer.SERVER_HOST);
		client.setServerPort(ChatServer.SERVER_PORT);
		client.run();
	}

	@Test
	public void clientTwo() throws Exception {
		ChatClient client = new ChatClient();
		client.setServerHost(ChatServer.SERVER_HOST);
		client.setServerPort(ChatServer.SERVER_PORT);
		client.run();
	}

	private String serverHost;
	private int serverPort;

	public void run() throws Exception {
		EventLoopGroup group = new NioEventLoopGroup();

		Bootstrap bootstrap = new Bootstrap()
				.group(group)
				.channel(NioSocketChannel.class)
				.handler(new ChannelInitializer<SocketChannel>() {
					@Override
					protected void initChannel(SocketChannel ch) throws Exception {
						ChannelPipeline pipeline = ch.pipeline();
						pipeline.addLast("decoder", new StringDecoder());
						pipeline.addLast("encoder", new StringEncoder());
						// 添加业务处理 handler
						pipeline.addLast(new ChatClientHandler());
					}
				});

		ChannelFuture channelFuture = bootstrap.connect(serverHost, serverPort).sync();

		Channel channel = channelFuture.channel();
		System.out.println("Netty Chat Client 启动：" + channel.localAddress());

		// 客户端需要输入信息，创建一个扫描器
		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNextLine()) {
			String msg = scanner.nextLine();
			if (msg.equals("exit")) {
				group.shutdownGracefully();
				break;
			}
			// 通过 channel 发送到服务器端
			channel.writeAndFlush(msg + "\r\n");
		}

		Thread.currentThread().join();
	}

}
