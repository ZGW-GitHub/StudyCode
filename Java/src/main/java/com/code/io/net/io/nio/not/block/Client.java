package com.code.io.net.io.nio.not.block;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

/**
 * @author NotUpToYou
 */
public class Client {

    private static final int PORT = 9999;
    private static final String HOST = "127.0.0.1";
    private static String USER_NAME;
    private static SocketChannel channel;

    public Client(String name) throws IOException, InterruptedException {

        USER_NAME = name;

        InetSocketAddress address = new InetSocketAddress(HOST, PORT);

        channel = SocketChannel.open();

        channel.configureBlocking(false);

        if (!channel.connect(address)) {
            while (!channel.finishConnect()) {
                // 没连接上，可以做些其他事
                Thread.sleep(2_000);
            }
        }

        String userAddress = channel.getLocalAddress().toString();

        System.out.println("------- " + userAddress + " 成功登录 -------");

    }

    // 发消息
    public void setMsg(String msg) throws IOException {

        if ("exit".equalsIgnoreCase(msg)) {
            channel.close();
            return;
        }

        msg = USER_NAME + " 说：" + msg;
        ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes(StandardCharsets.UTF_8));
        channel.write(buffer);

    }

    // 获取 Server 返回的消息
    public void getMsg() throws IOException {

        ByteBuffer buffer = ByteBuffer.allocate(1024);
        int length = channel.read(buffer);
        if (length > 0) {
            System.out.println(new String(buffer.array(), 0, length, StandardCharsets.UTF_8));
        }

    }

}
