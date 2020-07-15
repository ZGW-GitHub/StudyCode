/*
      Date:  2019-11-13 16:22
                                 */
package com.code.study.Netty.w3cschool.a_discard;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class DiscardServer {

    public static void main(String[] args) {

        NioEventLoopGroup boosGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        try {

            ChannelFuture channelFuture = new ServerBootstrap()
                    .group(boosGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new DiscardServerChannelHandler());
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .bind(28082).sync();

            channelFuture.channel().closeFuture().sync();

        } catch (InterruptedException e) {

            e.printStackTrace();
            boosGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();

        }

    }

}
