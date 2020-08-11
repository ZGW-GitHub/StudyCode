package com.code.io.file.io.nio;

import java.nio.ByteBuffer;

/**
 * Buffer 类维护了 4 个核心变量属性来提供关于缓冲区包含的数据的信息 :
 *
 * - 容量	Capacity
 *   - 缓冲区能够容纳的数据量 , 在缓冲区创建时被设定 , 并不可变 ( 因为底层是数组 )
 *
 * - 上界	Limit
 *   - 写缓冲区时 : Limit = Capacity
 *   - 读缓冲区时 : Limit = 缓冲区中的数据量大小
 *
 * - 位置	Position
 *   - 要操作的位置
 *
 * - 标记	Mark
 *   - 一个备忘位置 , 记录当前 Position 的位置 , 可通过 reset() 恢复到 mark 的位置
 *
 *
 * @author 愆凡
 * @date 2020/8/11 9:48 上午
 */
public class BufferDemoC {
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
