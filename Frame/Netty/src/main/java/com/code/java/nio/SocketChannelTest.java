package com.code.java.nio;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/**
 * @author 愆凡
 * @date 2021/04/16 5:02 下午
 */
public class SocketChannelTest {

	@Test
	public void serverTest() {
		new Server().start();
	}

	@Test
	public void clientTest() throws IOException, InterruptedException {
		Client client = new Client("小明");

		new Thread(() -> {
			while (true) {
				try {
					client.getMsg();
					Thread.sleep(2_000);
				} catch (IOException | InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();

		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNextLine()) {
			String msg = scanner.nextLine();
			client.setMsg(msg);
		}
	}

	private static final int PORT = 9999;
	private static final String HOST = "127.0.0.1";

	static class Server {
		private Selector selector;
		private ServerSocketChannel serverSocketChannel;

		private Server() {
			try {
				// 1、创建 ServerSocketChannel
				serverSocketChannel = ServerSocketChannel.open();
				// 2、创建 Selector
				selector = Selector.open();
				// 3、绑定端口
				serverSocketChannel.bind(new InetSocketAddress(PORT));
				// 4、设置为非阻塞（因为一个 Channel 要注册到 Selector 中，Channel 必须是非阻塞的）
				serverSocketChannel.configureBlocking(false);
				// 5、将 ServerSocketChannel 注册到 Selector ，并监听新连接事件（ServerSocketChannel 只需要监听新连接事件即可）
				serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		private void start() {
			try {
				while (true) {
					// 获取新连接事件就绪的 Channel 数量
					if ((selector.select(2_000)) == 0) {
						System.out.println("无新连接！");
						continue;
					}

					// 获取所有已就绪 Channel 对应的 SelectionKey
					Iterator<SelectionKey> keyIterator = selector.selectedKeys().iterator();
					while (keyIterator.hasNext()) {
						SelectionKey key = keyIterator.next();
						keyIterator.remove();

						// 忽略无效的 SelectionKey
						if (!key.isValid()) {
							continue;
						}

						// 处理新连接就绪事件
						if (key.isAcceptable()) {
							// a、接受客户端的连接，获取连接的客户端的 SocketChannel
							SocketChannel socketChannel = serverSocketChannel.accept();
							socketChannel.configureBlocking(false);
							// b、将新建的 SocketChannel 注册到 Selector，并监听读事件
							socketChannel.register(selector, SelectionKey.OP_READ);

							System.out.println(socketChannel.getRemoteAddress().toString() + " -> 上线了。。。");
						}

						// 处理读就绪事件（读取客户端发来的消息）
						if (key.isReadable()) {
							// a、通过 SelectionKey 获取与之关联的 Channel
							SocketChannel socketChannel = (SocketChannel) key.channel();
							ByteBuffer buffer = ByteBuffer.allocate(1024);
							int length = socketChannel.read(buffer);
							if (length > 0) {
								String msg = new String(buffer.array(), 0, length, StandardCharsets.UTF_8);
								printInfo(msg);
								// 广播
								setAnyBody(msg, socketChannel);
							}
						}
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		private void printInfo(String msg) {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			System.out.println(" [ " + simpleDateFormat + " ]  -> " + msg);
		}

		// 发广播的方法
		private void setAnyBody(String msg, SocketChannel channel) throws IOException {
			// 获取 Selector 所有的 SelectionKey
			Set<SelectionKey> keys = selector.keys();

			ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes(StandardCharsets.UTF_8));
			// 遍历
			for (SelectionKey key : keys) {
				SelectableChannel selectableChannel = key.channel();
				// 筛选掉 ServerSocketChannel 和自己
				if (selectableChannel instanceof SocketChannel && selectableChannel != channel) {
					SocketChannel c = (SocketChannel) selectableChannel;
					c.write(buffer);
				}

			}
		}

	}


	static class Client {
		private final String USER_NAME;
		private final SocketChannel channel;

		public Client(String name) throws IOException, InterruptedException {
			USER_NAME = name;
			InetSocketAddress address = new InetSocketAddress(HOST, PORT);
			// 创建 SocketChannel 用来和服务端通信
			channel = SocketChannel.open();
			channel.configureBlocking(false);

			if (!channel.connect(address)) {
				while (!channel.finishConnect()) {
					// 没连接上，可以做些其他事
					Thread.sleep(2_000);
				}
			}

			System.out.println("------- " + channel.getLocalAddress().toString() + " 成功登录 -------");
		}

		// 发消息
		public void setMsg(String msg) throws IOException {
			if ("exit".equalsIgnoreCase(msg)) {
				channel.close();
				return;
			}

			msg = USER_NAME + " 说：" + msg;
			ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes(StandardCharsets.UTF_8));
			channel.write(buffer);
		}

		// 获取 Server 返回的消息
		public void getMsg() throws IOException {
			ByteBuffer buffer = ByteBuffer.allocate(1024);
			int length = channel.read(buffer);
			if (length > 0) {
				System.out.println(new String(buffer.array(), 0, length, StandardCharsets.UTF_8));
			}
		}

	}


}
