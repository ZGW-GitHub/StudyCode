package com.code.study.Netty.demo.chat;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.util.Scanner;

public class ChatClient {

    public static void main(String[] args) {
        ChatClient chatClient = new ChatClient(8081, "127.0.0.1");
        chatClient.run();
    }

    private final int port;
    private final String host;

    public ChatClient(int port, String host) {
        this.port = port;
        this.host = host;
    }

    public void run() {

        NioEventLoopGroup group = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();

        try {
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();

                            pipeline.addLast("decoder", new StringDecoder()); // 解码器
                            pipeline.addLast("encoder", new StringEncoder());

                            pipeline.addLast(new ChatClientHandler());
                        }
                    });

            System.out.println("Client Ready !");

            // 启动客户都，连接服务器
            // sync() 方法是Future类的方法，用于阻塞获取执行结果
            ChannelFuture future = bootstrap.connect(host, port).sync();

            System.out.println("Client Start !");

            Channel channel = future.channel();

            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNextLine()) {
                String msg = scanner.nextLine();
                channel.writeAndFlush(msg + "\r\n");
            }

            // 关闭通道
            // sync() 方法是Future类的方法，用于阻塞获取执行结果
            ChannelFuture channelFuture = channel.closeFuture().sync();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {

            // 关闭线程组
            group.shutdownGracefully();

        }

    }

}
