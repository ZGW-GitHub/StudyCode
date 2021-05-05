package com.code.java.nio;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * {@link Selector} 示例
 *
 * @author 愆凡
 * @date 2021/4/20 21:07
 */
public class SelectorTest {

	public final String serverHost = "127.0.0.1";
	public final Integer serverPort = 7000;

	@Test
	@SuppressWarnings("all")
	public void clientTest() throws IOException {
		// 获取 Channel
		SocketChannel channel = SocketChannel.open();
		channel.configureBlocking(false);

		// 创建 Selector
		Selector selector = Selector.open();

		// 连接服务端
		boolean connected = channel.connect(new InetSocketAddress(serverHost, serverPort));
		SelectionKey selectionKey;
		if (connected) {
			// 连接成功，注册 Channel 到 Selector 中，并监听读事件
			selectionKey = channel.register(selector, SelectionKey.OP_READ);
		} else {
			selectionKey = channel.register(selector, SelectionKey.OP_CONNECT);
		}

		while (true) {
			// 通过 Selector 选择 Channel 
			int readyChannels = selector.select();
			if (readyChannels == 0) {
				continue;
			}

			// 获得可操作的 Channel
			Set<SelectionKey> selectedKeys = selector.selectedKeys();
			// 遍历 SelectionKey 数组
			Iterator<SelectionKey> keyIterator = selectedKeys.iterator();
			while (keyIterator.hasNext()) {
				SelectionKey key = keyIterator.next();
				// 移除（TODO 若没有删除，那么下一次调用 select 时, 这个 SelectionKey 还在 selectedKeys 中）
				keyIterator.remove();

				// 忽略无效的 SelectionKey
				if (!key.isValid()) {
					continue;
				}

				if (key.isAcceptable()) { // 仅服务端
					// a connection was accepted by a ServerSocketChannel.
				}
				if (key.isConnectable()) {
					// a connection was established with a remote server.
					System.err.println("key.isConnectable()");

					channel.register(selector, SelectionKey.OP_READ);
				}
				if (key.isReadable()) {
					// a channel is ready for reading
					System.err.println("key.isReadable()");
				}
				if (key.isWritable()) {
					// a channel is ready for writing
					System.err.println("key.isWritable()");
				}
			}
		}
	}

	@Test
	@SuppressWarnings("all")
	public void serverTest() throws IOException {
		// 获取 Channel
		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
		serverSocketChannel.configureBlocking(false);
		serverSocketChannel.bind(new InetSocketAddress(serverPort));

		// 创建 Selector
		Selector selector = Selector.open();

		SelectionKey selectionKey = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

		while (true) {
			// 通过 Selector 选择 Channel 
			int readyChannels = selector.select();
			if (readyChannels == 0) {
				continue;
			}

			// 获得可操作的 Channel
			Set<SelectionKey> selectedKeys = selector.selectedKeys();
			// 遍历 SelectionKey 数组
			Iterator<SelectionKey> keyIterator = selectedKeys.iterator();
			while (keyIterator.hasNext()) {
				SelectionKey key = keyIterator.next();
				// 移除（TODO 若没有删除，那么下一次调用 select 时, 这个 SelectionKey 还在 selectedKeys 中）
				keyIterator.remove();

				// 忽略无效的 SelectionKey
				if (!key.isValid()) {
					continue;
				}

				if (key.isAcceptable()) {
					// a connection was accepted by a ServerSocketChannel.
					System.err.println("key.isAcceptable()");

					SocketChannel socketChannel = serverSocketChannel.accept();
					socketChannel.configureBlocking(false);
					socketChannel.register(selector, SelectionKey.OP_READ);
				}
				if (key.isConnectable()) { // 仅客户端
					// a connection was established with a remote server.
				}
				if (key.isReadable()) {
					// a channel is ready for reading
					System.err.println("key.isReadable()");
				}
				if (key.isWritable()) {
					// a channel is ready for writing
					System.err.println("key.isWritable()");
				}
			}
		}
	}

}
