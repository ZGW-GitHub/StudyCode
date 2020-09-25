package com.code.io.file.io.nio;

import java.nio.ByteBuffer;

/**
 * Buffer 类维护了 4 个核心变量属性来提供关于缓冲区包含的数据的信息 :
 * <p>
 * - 容量	Capacity
 * - 缓冲区能够容纳的数据量 , 在缓冲区创建时被设定 , 并不可变 ( 因为底层是数组 )
 * <p>
 * - 上界	Limit
 * - 写缓冲区时 : Limit = Capacity
 * - 读缓冲区时 : Limit = 缓冲区中的数据量大小
 * <p>
 * - 位置	Position
 * - 要操作的位置
 * <p>
 * - 标记	Mark
 * - 一个备忘位置 , 记录当前 Position 的位置 , 可通过 reset() 恢复到 mark 的位置
 *
 * @author 愆凡
 * @date 2020/8/11 9:48 上午
 */
public class BufferDemoB {
	public static void main(String[] args) {

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
}
