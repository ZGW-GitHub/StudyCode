/*
      Date:  2019-08-08 17:02    
                                 */
package com.snow.study.Netty.a.code;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;

public class Server {

    public static void main(String[] args) throws InterruptedException {

        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap();

        serverBootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 128)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast("decoder", new ProtobufDecoder(BookMessage.Book.getDefaultInstance()));
                        pipeline.addLast(new ServerHandler());
                    }
                });

        ChannelFuture future = serverBootstrap.bind(9999).sync();

        Channel channel = future.channel();

        channel.closeFuture().sync();

        bossGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();

    }

}
