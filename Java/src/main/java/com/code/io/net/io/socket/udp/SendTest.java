package com.code.io.net.io.socket.udp;

import org.junit.Test;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * @author 愆凡
 * @date 2020/8/10 10:27 上午
 * <p>
 * UDP协议发送数据：
 * A:创建发送端Socket对象
 * B:创建数据，并把数据打包
 * C:调用Socket对象的发送方法发送数据包
 * D:释放资源
 *
 * @author 愆凡
 */
@SuppressWarnings("all")
public class SendTest {

	@Test
	public void test() throws IOException {
		byte[] datas = "Hello".getBytes(); // 创建数据
		InetAddress address = InetAddress.getByName("127.0.0.1"); // IP地址对象
		int port = 10086; // 端口
		
		// 1、创建发送端Socket对象
		DatagramSocket socket = new DatagramSocket();
		// 2、将数据打包
		DatagramPacket packet = new DatagramPacket(datas, datas.length, address, port);

		// 3、调用Socket对象的发送方法发送数据包
		socket.send(packet);

		// 4、释放资源
		socket.close();
	}

}
