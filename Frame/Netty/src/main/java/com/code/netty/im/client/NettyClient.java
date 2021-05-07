package com.code.netty.im.client;

import com.code.netty.im.client.handler.LoginResponseHandler;
import com.code.netty.im.client.handler.MessageResponseHandler;
import com.code.netty.im.codec.PacketDecoder;
import com.code.netty.im.codec.PacketEncode;
import com.code.netty.im.codec.Spliter;
import com.code.netty.im.protocol.request.MessageRequestPacket;
import com.code.netty.im.server.NettyServer;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@SuppressWarnings("all")
public class NettyClient {

	private static final NettyClient client = new NettyClient();

	private static final int MAX_RETRY = 5;

	@Test
	public void clientOne() throws Exception {
		connect(group, bootstrap, NettyServer.SERVER_HOST, NettyServer.SERVER_PORT, MAX_RETRY);

		Thread.currentThread().join();
	}

	@Test
	public void clientTwo() throws Exception {
		connect(group, bootstrap, NettyServer.SERVER_HOST, NettyServer.SERVER_PORT, MAX_RETRY);

		Thread.currentThread().join();
	}

	private final EventLoopGroup group;
	private final Bootstrap bootstrap;

	public NettyClient() {
		group = new NioEventLoopGroup();
		bootstrap = new Bootstrap()
				.group(group)
				.channel(NioSocketChannel.class)
				.handler(new ChannelInitializer<SocketChannel>() {
					@Override
					protected void initChannel(SocketChannel ch) throws Exception {
						ChannelPipeline pipeline = ch.pipeline();
						// 添加业务处理 handler
						pipeline.addLast(new Spliter());
						pipeline.addLast(new PacketDecoder());
						pipeline.addLast(new LoginResponseHandler());
						pipeline.addLast(new MessageResponseHandler());
						pipeline.addLast(new PacketEncode());
					}
				});
	}

	private void connect(EventLoopGroup group, Bootstrap bootstrap, String host, int port, int retry) {
		bootstrap.connect(host, port).addListener(future -> {
			if (future.isSuccess()) {
				log.info("Netty Client 启动成功");

				Channel channel = ((ChannelFuture) future).channel();
				new Thread(() -> clientSendMsgThread(group, channel)).start();

				channel.closeFuture().addListener((ChannelFutureListener) cf -> {
					log.info("Netty Client 关闭");

					group.shutdownGracefully();
				});
			} else if (retry == 0) {
				log.error("重试次数已用完，放弃连接！");
			} else {
				// 第几次重连
				int order = (MAX_RETRY - retry) + 1;
				// 本次重连的间隔
				int delay = 1 << order;

				log.warn(new Date() + ": 连接失败，第" + order + "次重连...");

				bootstrap.config().group().schedule(() -> connect(group, bootstrap, host, port, retry - 1), delay, TimeUnit.SECONDS);
			}
		});
	}

	private void clientSendMsgThread(EventLoopGroup group, Channel channel) {
		// 客户端需要输入信息，创建一个扫描器
		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNextLine()) {
			System.out.println("输入消息发送至服务端: ");

			String msg = scanner.nextLine();

			if (msg.equals("exit")) {
				group.shutdownGracefully();
				break;
			}

			MessageRequestPacket messageRequestPacket = new MessageRequestPacket();
			messageRequestPacket.setMessage(msg);
			channel.writeAndFlush(messageRequestPacket);
		}
	}

}
