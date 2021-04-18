package com.code.java.nio;

import org.junit.Test;

import java.nio.ByteBuffer;

/**
 * @author 愆凡
 * @date 2020/8/11 9:48 上午
 */
@SuppressWarnings("all")
public class BufferTest {

	@Test
	public void oneTest() {
		// 1、创建一个缓冲区
		ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

		// 2、初始时 4个核心变量 的值
		System.out.println("初始时 --> capacity ---> " + byteBuffer.capacity()); // 1024
		System.out.println("初始时 --> limit ---> " + byteBuffer.limit()); // 1024
		System.out.println("初始时 --> position ---> " + byteBuffer.position()); // 0
		System.out.println("初始时 --> mark ---> " + byteBuffer.mark() + "\n"); // java.nio.HeapByteBuffer[pos=0 lim=1024 cap=1024]

		// 3、添加一些数据到缓冲区中
		String s = "Java3y";
		byteBuffer.put(s.getBytes());

		// 4、写入数据后 4个核心变量 的值
		System.out.println("put完之后 --> capacity ---> " + byteBuffer.capacity()); // 1024
		System.out.println("put完之后 --> limit ---> " + byteBuffer.limit()); // 1024
		System.out.println("put完之后 --> position ---> " + byteBuffer.position()); // 6
		System.out.println("put完之后 --> mark ---> " + byteBuffer.mark() + "\n"); // java.nio.HeapByteBuffer[pos=6 lim=1024 cap=1024]

		// 5、这个方法可以改动 position 和 limit 的位置（ limit 变成了 position 的位置，而 position 变成了 0 ）
		// 一般称这个方法为：切换成读模式
		byteBuffer.flip();

		// 6、切换成读模式后 4个核心变量 的值
		System.out.println("切换成读模式后 --> capacity ---> " + byteBuffer.capacity()); // 1024
		System.out.println("切换成读模式后 --> limit ---> " + byteBuffer.limit()); // 6
		System.out.println("切换成读模式后 --> position ---> " + byteBuffer.position()); // 0
		System.out.println("切换成读模式后 --> mark ---> " + byteBuffer.mark() + "\n"); // java.nio.HeapByteBuffer[pos=0 lim=6 cap=1024]

		// 7、切换成读模式后就可以读取 Buffer 中的数据了
		byte[] bytes = new byte[byteBuffer.limit()]; // 创建一个 limit 大小的字节数组(因为就只有limit这么多个数据可读)
		byteBuffer.get(bytes); // 将读取的数据装进我们创建的字节数组中，同时更改 position 的值
		System.out.println(new String(bytes, 0, bytes.length) + "\n");

		// 8、读取数据后 4个核心变量 的值
		System.out.println("get完之后 --> capacity ---> " + byteBuffer.capacity()); // 1024
		System.out.println("get完之后 --> limit ---> " + byteBuffer.limit()); // 6
		System.out.println("get完之后 --> position ---> " + byteBuffer.position()); // 6
		System.out.println("get完之后 --> mark ---> " + byteBuffer.mark() + "\n"); // java.nio.HeapByteBuffer[pos=6 lim=6 cap=1024]

		// 9、读取完成后，还想写，可以调用 clear() 方法，注意该方法并不会将 Buffer 中原有数据清空，只是变更了 position 和 limit 的值
		byteBuffer.clear();

		// 10、切换回写模式时 4个核心变量 的值
		System.out.println("切换回写模式之后 --> capacity ---> " + byteBuffer.capacity()); // 1024
		System.out.println("切换回写模式之后 --> limit ---> " + byteBuffer.limit()); // 1024
		System.out.println("切换回写模式之后 --> position ---> " + byteBuffer.position()); // 0
		System.out.println("切换回写模式之后 --> mark ---> " + byteBuffer.mark()); // java.nio.HeapByteBuffer[pos=0 lim=1024 cap=1024]
	}

	@Test
	public void twoTest() {
		// 1、创建一个缓冲区
		ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

		// 2、添加一些数据到缓冲区中
		String s = "Java3y";
		byteBuffer.put(s.getBytes());

		// 3、切换成读模式
		byteBuffer.flip();

		// 4、切换成读模式后 4个核心变量 的值
		System.out.println("切换成读模式后 --> capacity ---> " + byteBuffer.capacity()); // 1024
		System.out.println("切换成读模式后 --> limit ---> " + byteBuffer.limit()); // 6
		System.out.println("切换成读模式后 --> position ---> " + byteBuffer.position()); // 0
		System.out.println("切换成读模式后 --> mark ---> " + byteBuffer.mark() + "\n"); // java.nio.HeapByteBuffer[pos=0 lim=6 cap=1024]

		// 5、读取 Buffer 中的数据了
		byte[] bytes = new byte[byteBuffer.limit()];
		byteBuffer.get(bytes);
		System.out.println(new String(bytes, 0, bytes.length) + "\n");

		// 6、读取数据后 4个核心变量 的值
		System.out.println("get完之后 --> capacity ---> " + byteBuffer.capacity()); // 1024
		System.out.println("get完之后 --> limit ---> " + byteBuffer.limit()); // 6
		System.out.println("get完之后 --> position ---> " + byteBuffer.position()); // 6
		System.out.println("get完之后 --> mark ---> " + byteBuffer.mark() + "\n"); // java.nio.HeapByteBuffer[pos=6 lim=6 cap=1024]

		// 7、读取完成后，还想再读取一次，可以调用 clear() 方法
		byteBuffer.rewind();

		// 8、重复读之前 4个核心变量 的值
		System.out.println("重复读之前 --> capacity ---> " + byteBuffer.capacity()); // 1024
		System.out.println("重复读之前 --> limit ---> " + byteBuffer.limit()); // 6
		System.out.println("重复读之前 --> position ---> " + byteBuffer.position()); // 0
		System.out.println("重复读之前 --> mark ---> " + byteBuffer.mark()); // java.nio.HeapByteBuffer[pos=0 lim=6 cap=1024]
	}

	@Test
	public void threeTest() {
// 1、创建一个缓冲区
		ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

		// 2、添加一些数据到缓冲区中
		String s = "Java3y";
		byteBuffer.put(s.getBytes());

		// 3、切换成读模式
		byteBuffer.flip();

		// 4、切换成读模式后 4个核心变量 的值
		System.out.println("切换成读模式后 --> capacity ---> " + byteBuffer.capacity()); // 1024
		System.out.println("切换成读模式后 --> limit ---> " + byteBuffer.limit()); // 6
		System.out.println("切换成读模式后 --> position ---> " + byteBuffer.position() + "\n"); // 0

		// 5、读取 Buffer 中的数据了
		byte[] bytes = new byte[byteBuffer.limit()];
		byteBuffer.get(bytes, 0, 4);
		System.out.println(new String(bytes, 0, 4) + "\n");

		// 6、标记
		byteBuffer.mark();

		// 6、第一次读取数据后 4个核心变量 的值
		System.out.println("第一次 get 后 --> capacity ---> " + byteBuffer.capacity()); // 1024
		System.out.println("第一次 get 后 --> limit ---> " + byteBuffer.limit()); // 6
		System.out.println("第一次 get 后 --> position ---> " + byteBuffer.position() + "\n"); // 4

		// 5、读取 Buffer 中的数据了
		byteBuffer.get(bytes, 4, 2);
		System.out.println(new String(bytes, 4, 2) + "\n");

		// 7、读取数据后 4个核心变量 的值
		System.out.println("第二次 get 后 --> capacity ---> " + byteBuffer.capacity()); // 1024
		System.out.println("第二次 get 后 --> limit ---> " + byteBuffer.limit()); // 6
		System.out.println("第二次 get 后 --> position ---> " + byteBuffer.position() + "\n"); // 6

		// 8、恢复到标记位置
		byteBuffer.reset();

		// 9、恢复到标记位置 4个核心变量 的值
		System.out.println("恢复到标记位置 --> capacity ---> " + byteBuffer.capacity()); // 1024
		System.out.println("恢复到标记位置 --> limit ---> " + byteBuffer.limit()); // 6
		System.out.println("恢复到标记位置 --> position ---> " + byteBuffer.position()); // 4
	}
	
}
