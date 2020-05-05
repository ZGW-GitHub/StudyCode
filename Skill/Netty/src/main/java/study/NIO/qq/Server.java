/*
      Date:  2019-08-02 15:29
                                 */
package study.NIO.qq;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.Set;

/**
 * @author NotUpToYou
 */
public class Server {

    private static ServerSocketChannel serverSocketChannel;

    private static Selector selector;

    private static final int PORT = 9999;

    private Server() {
        try {
            // 1、获取 ServerSocketChannel 监听所有通道
            serverSocketChannel = ServerSocketChannel.open();
            // 2、获取选择器
            selector = Selector.open();
            // 3、设置阻塞方式
            serverSocketChannel.configureBlocking(false);
            // 4、绑定端口
            serverSocketChannel.bind(new InetSocketAddress(PORT));
            // 5、将 ServerSocketChannel 注册给选择器
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void start() {

        try {

            while (true) {

                if ((selector.select(2_000)) == 0) {
//                    System.out.println("无连接！");
                    continue;
                }

                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();

                while (iterator.hasNext()) {

                    SelectionKey key = iterator.next();

                    if (key.isAcceptable()) { // 处理连接请求
                        SocketChannel socketChannel = serverSocketChannel.accept();
                        socketChannel.configureBlocking(false);
                        socketChannel.register(selector, SelectionKey.OP_READ);
                        System.out.println(socketChannel.getRemoteAddress().toString() + " -> 上线了。。。");
                    }
                    if (key.isReadable()) { // 处理读请求
                        getMsg(key);
                    }

                    iterator.remove();

                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // 读取客户端发送的消息，并广播
    private void getMsg(SelectionKey key) throws IOException {
        SocketChannel socketChannel = (SocketChannel) key.channel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        int length = socketChannel.read(buffer);
        if (length > 0) {
            String msg = new String(buffer.array(), 0, length, StandardCharsets.UTF_8);
            printInfo(msg);
            // 广播
            setAnyBody(msg, socketChannel);
        }
    }

    // 发广播的方法
    private void setAnyBody(String msg, SocketChannel channel) throws IOException {
        Set<SelectionKey> keys = selector.keys();
        ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes(StandardCharsets.UTF_8));
        for (SelectionKey key : keys) {
            SelectableChannel selectableChannel = key.channel();
            if (selectableChannel instanceof SocketChannel && selectableChannel != channel) {
                SocketChannel c = (SocketChannel) selectableChannel;
                c.write(buffer);
            }

        }
    }

    private void printInfo(String msg) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(" [ " + simpleDateFormat + " ]  -> " + msg);
    }

    public static void main(String[] args) {

        new Server().start();

    }

}
