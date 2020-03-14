/*
      Date:  2019-08-08 12:37    
                                 */
package study.Netty.a.chat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class ChatServer {

    public static void main(String[] args) {
        ChatServer chatServer = new ChatServer(8081);
        chatServer.run();
    }

    private final int port;

    public ChatServer(int port) {
        this.port = port;
    }

    public void run() {

        // 创建线程组
        EventLoopGroup boosGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        // 创建服务端启动助手来配置参数
        ServerBootstrap serverBootstrap = new ServerBootstrap();

        try {

            serverBootstrap.group(boosGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    // 创建通道初始化对象
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {

                            ChannelPipeline pipeline = ch.pipeline(); // 获取 Handler 链，即：pipeline

                            // 向 pipeline 中添加一个解码器
                            pipeline.addLast("decoder", new StringDecoder());
                            // 向 pipeline 中添加一个编码器
                            pipeline.addLast("encoder", new StringEncoder());

                            pipeline.addLast(new ChatServerHandler()); // 添加自定义的 Handler

                        }
                    });

            System.out.println("Server Ready ！");

            ChannelFuture future = serverBootstrap.bind(port).sync();

            System.out.println("Server Start ！");

            // 关闭通道，关闭线程组
            future.channel().closeFuture().sync();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            boosGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
            System.out.println("Server 关闭");
        }

    }

}
