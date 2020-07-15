package com.code.study.NIO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

/**
 * @author NotUpToYou
 */
public class Client {

    public static void main(String[] args) throws IOException {

        // 1、提供服务器 IP 地址与端口号
        InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1", 5678);

        // 2、获取一个网络通道
        SocketChannel channel = SocketChannel.open();

        // 3、设置是否阻塞（false：非阻塞）
        channel.configureBlocking(false);

        // 4、连接服务器
        if (!channel.connect(inetSocketAddress)) {
            while (!channel.finishConnect()) {
                // NIO 作为非阻塞式的优势所在
                System.out.println("在没连接服务器上时，客户端可以在这里做一些事情！");
            }
        }

        // 5、得到一个缓冲器并存入数据
        String msg = "Hello NIO !";
        ByteBuffer oBuffer = ByteBuffer.wrap(msg.getBytes(StandardCharsets.UTF_8));

        // 6、发送数据
        channel.write(oBuffer);

        // 7、在这里先不关闭连接，因为服务端会出异常
        System.in.read();

    }

}
