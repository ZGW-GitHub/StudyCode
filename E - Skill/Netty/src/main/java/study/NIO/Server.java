/*
      Date:  2019-08-02 15:14    
                                 */
package com.snow.study.NIO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

/**
 *  SelectionKey，代表了 Selector 和网络通道的注册关系,一共四种：
 *       intOP_ACCEPT：有新的网络连接可以 accept，值为 16
 *       intOP_CONNECT：代表连接已经建立，值为 8
 *       intOP_READ：代表了读操作，值为 1
 *       intOP_WRITE：代表了写操作，值为 4
 */
public class Server {

    public static void main(String[] args) throws IOException {


        // 1、端口号
        InetSocketAddress address = new InetSocketAddress(5678);

        // 2、得到 ServerSocketChannel 对象
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        // 3、得到 Selector 对象
        Selector selector = Selector.open();

        // 4、绑定端口号
        serverSocketChannel.bind(address);

        // 5、设置阻塞方式(false:非阻塞)
        serverSocketChannel.configureBlocking(false);

        // 6、将 ServerSocketChannel 对象注册给 Selector 对象
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        // 7、干活
        while (true) {

            // a、监控客户端
            if (selector.select(2_000) == 0) {
                System.out.println("Server：无客户端连接，可以别的事情。也是非阻塞的优势所在");
                continue;
            }
            // b、获取 SelectionKey ，判断通道的事件
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {

                SelectionKey selectionKey = iterator.next();

                if (selectionKey.isAcceptable()) { // SocketChannel连接事件
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                }

                if (selectionKey.isReadable()) { // SocketChannel读事件
                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                    ByteBuffer buffer = (ByteBuffer) selectionKey.attachment();
                    int length = socketChannel.read(buffer);
                    System.out.println(new String(buffer.array(), 0, length, StandardCharsets.UTF_8));
                }

                // d、移除当前 key
                iterator.remove();

            }

        }

    }


}

