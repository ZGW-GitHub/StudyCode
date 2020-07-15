package com.code.study.BIO;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws IOException {

        Socket socket = new Socket("127.0.0.1", 5678);

        while (true) {

            // 1、获取输出流
            OutputStream outputStream = socket.getOutputStream();

            // 2、获取输入
            Scanner scanner = new Scanner(System.in);
            System.out.println("请输入：");

            // 3、输入流读取
            outputStream.write(scanner.nextLine().getBytes());

            // 4、获取输入流
            InputStream inputStream = socket.getInputStream();

            byte[] bytes = new byte[1024];

            // 5、输入流读取
            inputStream.read(bytes);

            System.out.println(new String(bytes).trim());

            // 6、关闭
            socket.close();

        }

    }

}
