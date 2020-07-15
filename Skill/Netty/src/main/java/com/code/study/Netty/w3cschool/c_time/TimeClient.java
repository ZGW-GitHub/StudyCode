/*
      Date:  2019-11-13 18:31
                                 */
package com.code.study.Netty.w3cschool.c_time;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class TimeClient {
    public static void main(String[] args) {

        EventLoopGroup group = new NioEventLoopGroup();

        try {
            ChannelFuture channelFuture = new Bootstrap()
                    .group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new TimeDecoder(), new TimeClientChannelHandler());
                        }
                    })
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .connect("localhost", 28082)
                    .sync();
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
            group.shutdownGracefully();
        }

    }
}
