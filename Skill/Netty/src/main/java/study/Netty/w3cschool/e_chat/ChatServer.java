/*
      Date:  2019-11-14 9:33
                                 */
package com.snow.study.Netty.w3cschool.e_chat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.ServerSocketChannel;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChatServer {

    private final Logger logger = LoggerFactory.getLogger(ChatServer.class);

    private int port;

    public ChatServer(int port) {
        this.port = port;
    }

    public void run() {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ChannelFuture channelFuture = new ServerBootstrap()
                    .group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline channelPipeline = ch.pipeline();
                            channelPipeline.addLast("framer", new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
                            channelPipeline.addLast("decoder", new StringDecoder());
                            channelPipeline.addLast("encoder", new StringEncoder());
                            channelPipeline.addLast("handler", new ChatServerChannelHandler());

                            System.out.println("Client : [ " + ch.remoteAddress() + " ] 连接上！");
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .bind(this.port)
                    .sync();
            // 等待服务器 socket 关闭，在本例中，这不会发生，但你可以优雅的关闭你的服务器
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
            logger.info("Server 关闭了！");
        }
    }

    public static void main(String[] args) {
        int port;
        if (args.length > 0) {
            port = Integer.valueOf(args[0]);
        } else {
            port = 28082;
        }
        new ChatServer(port).run();
    }

}
