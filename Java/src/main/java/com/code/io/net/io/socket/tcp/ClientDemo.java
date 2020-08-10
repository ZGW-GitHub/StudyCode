package com.code.io.net.io.socket.tcp;

import java.net.Socket;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @author 愆凡
 * @date 2020/8/10 10:26 上午
 *
 * TCP协议发送数据：
 * 		A:创建发送端的Socket对象，这一步如果成功，就说明连接已经建立成功了
 * 		B:获取输出流，写数据
 * 		C:释放资源
 */
public class ClientDemo {
	public static void main(String[] args) throws IOException {

		// 创建发送端的Socket对象
		Socket socket = new Socket("192.168.12.92", 8888);

		// 获取输出流，写数据
		OutputStream os = socket.getOutputStream();
		os.write("hello,tcp,我来了".getBytes());

		// 释放资源
		socket.close();

	}
}
