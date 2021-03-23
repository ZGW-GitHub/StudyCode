package com.code.io.net.io.socket.tcp;

import org.junit.Test;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @author 愆凡
 * @date 2020/8/10 10:26 上午
 * <p>
 * TCP协议发送数据：
 * A:创建发送端的Socket对象，这一步如果成功，就说明连接已经建立成功了
 * B:获取输出流，写数据
 * C:释放资源
 */
public class ClientTest {
	
	@Test
	public void test() throws IOException {
		// 创建发送端的Socket对象
		Socket socket = new Socket("127.0.0.1", 8888);

		// 获取输出流，写数据
		OutputStream os = socket.getOutputStream();
		os.write("Hello".getBytes());

		// 释放资源
		socket.close();
	}
}
