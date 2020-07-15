package com.code.study.Netty.a.code;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufEncoder;

public class Client {

    public static void main(String[] args) throws InterruptedException {

        NioEventLoopGroup group = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();

        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast("encoder", new ProtobufEncoder());
                        pipeline.addLast(new ClientHandler());
                    }
                });

        // 启动客户端
        ChannelFuture future = bootstrap.connect("127.0.0.1", 9999).sync();

        Channel channel = future.channel();

        // 等待连接关闭
        channel.closeFuture().sync();

        group.shutdownGracefully();

    }

}
