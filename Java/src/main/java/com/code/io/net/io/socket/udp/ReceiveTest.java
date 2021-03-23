package com.code.io.net.io.socket.udp;

import org.junit.Test;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * @author 愆凡
 * @date 2020/8/10 10:29 上午
 * <p>
 * UDP协议接收数据：
 * A:创建接收端Socket对象
 * B:创建一个数据包(接收容器)
 * C:调用Socket对象的接收方法接收数据
 * D:解析数据包，并显示在控制台
 * E:释放资源
 *
 * @author 愆凡
 */
@SuppressWarnings("all")
public class ReceiveTest {

	@Test
	public void test() throws IOException {
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
