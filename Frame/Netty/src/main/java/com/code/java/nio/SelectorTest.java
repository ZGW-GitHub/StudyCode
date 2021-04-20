package com.code.java.nio;

import org.junit.Test;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
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

	@Test
	@SuppressWarnings("all")
	public void test() throws IOException {
		// 创建 Selector
		Selector selector = Selector.open();

		// 获取 Channel
		SocketChannel channel = SocketChannel.open();

		// 注册 Channel 到 Selector 中
		channel.configureBlocking(false);
		SelectionKey selectionKey = channel.register(selector, SelectionKey.OP_READ);

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
				if (key.isAcceptable()) {
					// a connection was accepted by a ServerSocketChannel.
				} else if (key.isConnectable()) {
					// a connection was established with a remote server.
				} else if (key.isReadable()) {
					// a channel is ready for reading
				} else if (key.isWritable()) {
					// a channel is ready for writing
				}
				// 移除（TODO 没有删除，那么下一次调用 select 时, 这个 SelectionKey 还在 selectedKeys 中）
				keyIterator.remove();
			}
		}
	}

}
