package com.code.io.net.io.socket.udp;

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
public class SendDemo {
	public static void main(String[] args) throws IOException {

		// 创建发送端Socket对象
		DatagramSocket socket = new DatagramSocket();

		// 创建数据
		byte[] bys = "hello,udp,我来了".getBytes();
		// 长度
		int length = bys.length;
		// IP地址对象
		InetAddress address = InetAddress.getByName("192.168.12.92");
		// 端口
		int port = 10086;
		// 将数据打包
		DatagramPacket packet = new DatagramPacket(bys, length, address, port);

		// 调用Socket对象的发送方法发送数据包
		socket.send(packet);

		// 释放资源
		socket.close();

	}
}
