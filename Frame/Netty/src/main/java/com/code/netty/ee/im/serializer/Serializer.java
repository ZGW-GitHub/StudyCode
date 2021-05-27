package com.code.netty.ee.im.serializer;

import com.code.netty.ee.im.serializer.impl.JsonSerializer;

/**
 * @author 愆凡
 * @date 2021/5/5 22:09
 */
public interface Serializer {

	Serializer DEFAULT = new JsonSerializer();

	/**
	 * 获取序列化算法
	 *
	 * @return 序列化算法
	 */
	byte getSerializerAlgorithm();

	/**
	 * 将 java 对象转换成二进制
	 *
	 * @param object java 对象
	 * @return 二进制
	 */
	byte[] serialize(Object object);

	/**
	 * 将二进制转换成 java 对象
	 *
	 * @param clazz java 对象的 class 类型
	 * @param bytes 二进制
	 * @param <T> T
	 * @return java 对象
	 */
	<T> T deserialize(Class<T> clazz, byte[] bytes);

}
