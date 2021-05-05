package com.code.netty.im;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.Setter;
import org.junit.Test;

import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * Netty 群聊系统案例
 *
 * @author 愆凡
 * @date 2021/4/19 17:24
 */
@Setter
@SuppressWarnings("all")
public class ChatClient {

	private static final int MAX_RETRY = 5;

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
						// 添加业务处理 handler
						pipeline.addLast(new ChatClientHandler());
					}
				});

		ChannelFuture channelFuture = connect(bootstrap, serverHost, serverPort, MAX_RETRY);

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

	private ChannelFuture connect(Bootstrap bootstrap, String host, int port, int retry) {
		return bootstrap.connect(host, port).addListener(future -> {
			if (future.isSuccess()) {
				System.out.println("连接成功!");
			} else if (retry == 0) {
				System.err.println("重试次数已用完，放弃连接！");
			} else {
				// 第几次重连
				int order = (MAX_RETRY - retry) + 1;
				// 本次重连的间隔
				int delay = 1 << order;

				System.err.println(new Date() + ": 连接失败，第" + order + "次重连...");

				bootstrap.config().group().schedule(() -> connect(bootstrap, host, port, retry - 1), delay, TimeUnit.SECONDS);
			}
		});
	}

}
