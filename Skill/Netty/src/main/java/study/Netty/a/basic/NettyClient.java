/*
      Date:  2019-08-02 21:27    
                                 */
package study.Netty.a.basic;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NettyClient {

    public static void main(String[] args) throws InterruptedException {

        // 1、创建一个线程组
        EventLoopGroup group = new NioEventLoopGroup();

        // 2、创建一个客户端启动助手，并配置
        Bootstrap bootstrap = new Bootstrap();

        bootstrap.group(group)
                .channel(NioSocketChannel.class) // 使用 NioSocketChannel 作为客户端通道的实现
                .handler(new ChannelInitializer<SocketChannel>() { // 创建初始化通道对象
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        ChannelPipeline pipeline = socketChannel.pipeline(); // 获取 Handler 链
                        pipeline.addLast(new NettyClientHandler()); // 将自定义的 Handler 添加到链中
                    }
                });

        // 3、启动客户端，连接服务器
        // sync() 方法是Future类的方法，用于阻塞获取执行结果
        ChannelFuture future = bootstrap.connect("127.0.0.1", 9999).sync();

        // 4、等待连接关闭
        // sync() 方法是Future类的方法，用于阻塞获取执行结果
//        future.channel().close().sync();

    }

}

