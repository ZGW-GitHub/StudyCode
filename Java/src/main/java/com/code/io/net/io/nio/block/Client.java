package com.code.io.net.io.nio.block;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @author 愆凡
 * @date 2020/8/11 4:05 下午
 */
public class Client {
	public static void main(String[] args) {

		try (SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9999));
			 FileChannel fileChannel = FileChannel.open(Paths.get("./test.txt"), StandardOpenOption.READ)) {

			ByteBuffer buffer = ByteBuffer.allocate(1024);

			while (fileChannel.read(buffer) != -1) {
				buffer.flip();
				socketChannel.write(buffer);
				buffer.clear();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
