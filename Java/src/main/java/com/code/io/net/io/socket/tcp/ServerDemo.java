package com.code.io.net.io.socket.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author 愆凡
 * @date 2020/8/10 10:43 上午
 * <p>
 * TCP协议接收数据：
 * A:创建接收端的Socket对象
 * B:监听客户端连接。返回一个对应的Socket对象
 * C:获取输入流，读取数据显示在控制台
 * D:释放资源
 *
 * @author 愆凡
 */
public class ServerDemo {
	public static void main(String[] args) throws IOException {

		// 创建接收端的Socket对象
		ServerSocket serverSocket = new ServerSocket(8888);

		// 监听客户端连接。返回一个对应的Socket对象
		Socket socket = serverSocket.accept(); // 侦听并接受到此套接字的连接。此方法在连接传入之前一直阻塞。

		// 获取输入流，读取数据显示在控制台
		InputStream is = socket.getInputStream();

		byte[] bys = new byte[1024];
		int len = is.read(bys); // 阻塞式方法
		String str = new String(bys, 0, len);

		String ip = socket.getInetAddress().getHostAddress();

		System.out.println(ip + "---" + str);

		// 释放资源
		socket.close();
		// ss.close(); //这个不应该关闭

	}
}
