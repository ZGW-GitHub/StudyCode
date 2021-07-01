package com.code.dubbo.spi.wrapper;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 愆凡
 * @date 2021/7/1 10:41
 */
@Slf4j
@AllArgsConstructor
public class SerializabWrapper implements ISerializable {

	private ISerializable serializable;

	@Override
	public String serialize(Object obj) {
		System.out.println("通用逻辑");

		return serializable.serialize(obj);
	}

	@Override
	public <T> T deserialize(Class<T> clazz, String str) {
		System.out.println("通用逻辑");

		return serializable.deserialize(clazz, str);
	}
}
