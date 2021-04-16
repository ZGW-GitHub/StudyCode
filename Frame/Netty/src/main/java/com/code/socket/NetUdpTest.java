package com.code.socket;

import org.junit.Test;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * @author 愆凡
 * @date 2021/04/16 4:58 下午
 */
@SuppressWarnings("all")
public class NetUdpTest {

	@Test
	public void sendTest() throws IOException {
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

	@Test
	public void receiveTest() throws IOException {
		// 创建接收端Socket对象
		DatagramSocket socket = new DatagramSocket(10086);

		// 创建一个数据包(接收容器)
		byte[] bys = new byte[1024];
		int length = bys.length;
		DatagramPacket packet = new DatagramPacket(bys, length);

		// 调用Socket对象的接收方法接收数据
		socket.receive(packet); // 阻塞式

		String s = new String(packet.getData(), 0, packet.getLength());
		System.out.println(packet.getAddress().getHostAddress() + " 传递的数据是: " + s);

		// 释放资源，接收端应该一直开着等待接收数据，是不需要关闭
//		socket.close();
	}
	
}
