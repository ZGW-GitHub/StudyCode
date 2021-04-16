package com.code.socket;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author 愆凡
 * @date 2021/04/16 4:58 下午
 */
@SuppressWarnings("all")
public class NetTcpTest {

	@Test
	public void clientTest() throws IOException {
		// 创建发送端的Socket对象
		Socket socket = new Socket("127.0.0.1", 8888);

		// 获取输出流，写数据
		OutputStream os = socket.getOutputStream();
		os.write("Hello".getBytes());

		// 释放资源
		socket.close();
	}

	@Test
	public void serverTest() throws IOException {
		// 创建接收端的Socket对象
		ServerSocket serverSocket = new ServerSocket(8888);

		// 监听客户端连接，返回一个对应的Socket对象。此方法在连接传入之前一直阻塞。
		Socket socket = serverSocket.accept();

		// 获取输入流，读取数据显示在控制台
		InputStream is = socket.getInputStream();

		byte[] bys = new byte[1024];
		int len = is.read(bys); // 阻塞式方法
		String str = new String(bys, 0, len);

		String ip = socket.getInetAddress().getHostAddress();

		System.out.println(ip + " ---> " + str);

		// 释放资源
		socket.close();
		// ss.close(); //这个不应该关闭
	}
	
}
