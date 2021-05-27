package com.code.netty.ee.im.serializer;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 序列化算法枚举类
 *
 * @author 愆凡
 * @date 2021/5/5 22:32
 */
@Getter
@AllArgsConstructor
public enum SerializerAlgorithmEnum {

	/**
	 * Json 序列化标识
	 */
	JSON((byte) 1, Serializer.DEFAULT);

	private final Byte type;
	private final Serializer serializer;

	public static Serializer getSerializer(Byte type) throws RuntimeException {
		SerializerAlgorithmEnum[] enums = SerializerAlgorithmEnum.values();
		for (SerializerAlgorithmEnum e : enums) {
			if (e.getType().equals(type)) {
				return e.getSerializer();
			}
		}

		throw new RuntimeException("没有对应的序列化算法");
	}

}
