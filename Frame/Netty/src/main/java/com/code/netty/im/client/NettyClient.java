package com.code.netty.im.client;

import com.code.netty.im.client.handler.LoginResponseHandler;
import com.code.netty.im.client.handler.MessageResponseHandler;
import com.code.netty.im.codec.PacketDecoder;
import com.code.netty.im.codec.PacketEncode;
import com.code.netty.im.codec.Spliter;
import com.code.netty.im.protocol.request.LoginRequestPacket;
import com.code.netty.im.protocol.request.MessageRequestPacket;
import com.code.netty.im.server.NettyServer;
import com.code.netty.im.server.session.SessionUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.net.InetSocketAddress;
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

	@Test
	public void clientOne() throws Exception {
		connect(group, bootstrap);

		Thread.currentThread().join();
	}

	@Test
	public void clientTwo() throws Exception {
		connect(group, bootstrap);

		Thread.currentThread().join();
	}

	private static final NettyClient client = new NettyClient();

	private final EventLoopGroup group;
	private final Bootstrap bootstrap;

	public NettyClient() {
		group = new NioEventLoopGroup();
		bootstrap = new Bootstrap()
				.group(group)
				.remoteAddress(new InetSocketAddress(NettyServer.SERVER_HOST, NettyServer.SERVER_PORT))
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

	private final int maxRetry = 5;

	private void connect(EventLoopGroup group, Bootstrap bootstrap) {
		connect(group, bootstrap, 1);
	}

	private void connect(EventLoopGroup group, Bootstrap bootstrap, Integer retry) {
		bootstrap.connect().addListener(future -> {
			if (future.isSuccess()) {
				log.info("Netty Client 启动成功");

				Channel channel = ((ChannelFuture) future).channel();
				new Thread(() -> clientSendMsgThread(group, channel)).start();

				channel.closeFuture().addListener((ChannelFutureListener) cf -> {
					log.info("Netty Client 关闭");

					group.shutdownGracefully();
				});
			} else if (retry == maxRetry) {
				log.error("重试次数已用完，放弃连接！");
			} else {
				// 本次重连的间隔
				int delay = 1 << retry;

				log.warn(new Date() + ": 连接失败，第" + retry + "次重连...");

				bootstrap.config().group().schedule(() -> connect(group, bootstrap, retry + 1), delay, TimeUnit.SECONDS);
			}
		});
	}

	private void clientSendMsgThread(EventLoopGroup group, Channel channel) {
		Scanner scanner = new Scanner(System.in);
		LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
		while (!Thread.interrupted()) {
			if (!SessionUtil.hasLogin(channel)) {
				System.out.print("输入用户名登录: ");
				String username = scanner.nextLine();

				loginRequestPacket.setUsername(username);
				loginRequestPacket.setPassword("pass");

				channel.writeAndFlush(loginRequestPacket);
				waitForLoginResponse();
			} else {
				String msg = scanner.nextLine();
				if (!msg.contains("：")) {
					System.err.println("消息格式不正确");
					continue;
				}
				String[] msgs = msg.split("：");

				channel.writeAndFlush(new MessageRequestPacket(msgs[0], msgs[1]));
			}
		}
	}

	private void waitForLoginResponse() {
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
