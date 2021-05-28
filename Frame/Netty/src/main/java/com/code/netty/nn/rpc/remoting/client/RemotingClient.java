package com.code.netty.nn.rpc.remoting.client;

import com.code.netty.nn.rpc.remoting.client.handler.HeartBeatTimerHandler;
import com.code.netty.nn.rpc.remoting.client.handler.MessageResponseHandler;
import com.code.netty.nn.rpc.remoting.codec.PacketCodecHandler;
import com.code.netty.nn.rpc.remoting.codec.Spliter;
import com.code.netty.nn.rpc.remoting.server.RemotingServer;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.net.InetSocketAddress;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Netty 群聊系统案例
 *
 * @author 愆凡
 * @date 2021/4/19 17:24
 */
@Slf4j
@SuppressWarnings("all")
public class RemotingClient {

	@Test
	public void clientOne() throws Exception {
		connect(group, bootstrap);

		Thread.currentThread().join();
	}

	private static final RemotingClient client = new RemotingClient();

	private final EventLoopGroup group;
	private final Bootstrap bootstrap;

	public RemotingClient() {
		group = new NioEventLoopGroup();
		bootstrap = new Bootstrap()
				.group(group)
				.remoteAddress(new InetSocketAddress(RemotingServer.SERVER_HOST, RemotingServer.SERVER_PORT))
				.channel(NioSocketChannel.class)
				.handler(new ChannelInitializer<SocketChannel>() {
					@Override
					protected void initChannel(SocketChannel ch) throws Exception {
						ChannelPipeline pipeline = ch.pipeline();
						pipeline.addLast(new IdleStateHandler(10, 10, 10, TimeUnit.SECONDS));
						pipeline.addLast(new Spliter());
						pipeline.addLast(new PacketCodecHandler());
						pipeline.addLast(new MessageResponseHandler());
						pipeline.addLast(new HeartBeatTimerHandler());
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

	}

}
