package com.code.study.Netty.a.basic;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class NettyServer {

    public static void main(String[] args) throws InterruptedException {

        // 1、创建用于接收客户端连接的线程组
        EventLoopGroup bossGroup = new NioEventLoopGroup();

        // 2、创建用于处理网络操作的线程组
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        // 3、创建服务端启动助手来配置参数
        ServerBootstrap serverBootstrap = new ServerBootstrap();

        serverBootstrap.group(bossGroup, workerGroup) // 设置两个线程组
                .channel(NioServerSocketChannel.class) // 使用 NioServerSocketChannel 作为服务器端通道的实现
                .option(ChannelOption.SO_BACKLOG, 128) // 设置等待队列大小
                .childOption(ChannelOption.SO_KEEPALIVE, true) // 保持活动连接状态
                .handler(new ChannelInitializer<SocketChannel>() { // 创建通道初始化对象
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        ChannelPipeline pipeline = socketChannel.pipeline(); // 获取 Handler 链
                        pipeline.addLast(new NettyServerHandler()); // 添加自定义的 Handler
                    }
                });

        System.out.println("------- Server is ready -------");

        // 4、绑定端口号
        // bind() 方法是异步的 sync() 方法是Future类的方法，用于阻塞获取执行结果
        // 这里的 Future 是 nio 包下的，不是 并发包 下的
        ChannelFuture future = serverBootstrap.bind(9999).sync();

        System.out.println("------- Server is starting -------");

        // 5、关闭通道，关闭线程组
        // sync() 方法是Future类的方法，用于阻塞获取执行结果
        future.channel().closeFuture().sync(); // 使用异步方式

        bossGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();

    }

}
