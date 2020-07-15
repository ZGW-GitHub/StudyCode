package com.code.study.BIO;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws IOException {

        // 创建 ServerSocket
        ServerSocket serverSocket = new ServerSocket(5678);

        while (true) {

            // 1、监听客户端
            Socket socket = serverSocket.accept(); // 阻塞

            // 2、获取输入流
            InputStream inputStream = socket.getInputStream(); // 阻塞

            byte[] bytes = new byte[1024];

            // 3、读取输入流
            inputStream.read(bytes);
            System.out.println(new String(bytes).trim());

            // 4、获取输出流
            OutputStream outputStream = socket.getOutputStream();

            // 5、向输出流写消息
            outputStream.write("OK!".getBytes());

            // 6、关闭
            socket.close();

        }

    }

}
