package com.code.dubbo.spi.simple;

import lombok.extern.slf4j.Slf4j;

/**
 * @author 愆凡
 * @date 2021/7/1 10:41
 */
@Slf4j
public class SerializableImplB implements ISerializable {
	@Override
	public String serialize(Object obj) {
		return "SerializableImplB";
	}

	@Override
	public <T> T deserialize(Class<T> clazz, String str) {
		return null;
	}
}
