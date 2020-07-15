/*
      Date:  2019-11-14 9:32
                                 */
package com.code.study.Netty.w3cschool.e_chat;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ChatClient {

    private String host;
    private int port;

    public ChatClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void run() {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            ChannelFuture channelFuture = new Bootstrap()
                    .group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline channelPipeline = ch.pipeline();
                            channelPipeline.addLast("framer", new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
                            channelPipeline.addLast("decoder", new StringDecoder());
                            channelPipeline.addLast("encoder", new StringEncoder());
                            channelPipeline.addLast("handler", new ChatClientChannelHandler());
                        }
                    })
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .connect(host, port)
                    .sync();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                channelFuture.channel().writeAndFlush(bufferedReader.readLine() + "\r\n");
            }
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
            group.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        new ChatClient("localhost", 28082).run();
    }

}
