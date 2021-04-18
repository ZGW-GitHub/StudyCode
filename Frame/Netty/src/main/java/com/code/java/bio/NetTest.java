package com.code.java.bio;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * BIO 示例
 *
 * @author 愆凡
 * @date 2021/4/18 14:27
 */
public class NetTest {

	@Test
	@SuppressWarnings("all")
	public void serverTest() throws IOException {
		// 1、创建一个线程池，如果有客户端连接，就创建一个线程，与之通讯
		ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();
		// 2、创建ServerSocket
		ServerSocket serverSocket = new ServerSocket(8888);
		System.out.println("服务器启动了");

		while (true) {
			System.out.println("Threadid = " + Thread.currentThread().getId() + "；ThreadName = " + Thread.currentThread().getName());
			// 3、监听，等待客户端连接
			System.out.println("等待连接....");
			final Socket socket = serverSocket.accept();

			System.out.println("连接到一个客户端：" + ToStringBuilder.reflectionToString(socket));
			// 4、连接到一个客户端，就创建一个线程，与之通讯(单独写一个方法)
			newCachedThreadPool.execute(() -> handler(socket));
		}
	}

	/**
	 * 处理客户端的连接
	 * @param socket {@link Socket}
	 */
	public void handler(Socket socket) {
		try {
			System.err.println("Threadid = " + Thread.currentThread().getId() + "；ThreadName = " + Thread.currentThread().getName());
			// 通过 Socket 获取输入流
			InputStream inputStream = socket.getInputStream();

			// 循环的读取客户端发送的数据
			byte[] bytes = new byte[1024];
			StringBuilder builder = new StringBuilder();
			while (true) {
				System.err.println("read....");
				int read = inputStream.read(bytes);
				if (read != -1) {
					builder.append(new String(bytes, 0, read)); // 输出客户端发送的数据
				} else {
					break;
				}
			}
			System.err.println("接收到的数据：" + builder);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.err.println("关闭和 Client 的连接");
			try {
				socket.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

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

}
