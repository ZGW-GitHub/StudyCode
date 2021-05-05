package com.code.netty.im.serializer;

import com.alibaba.fastjson.JSONObject;

/**
 * @author 愆凡
 * @date 2021/5/5 22:34
 */
public class JsonSerializer implements Serializer {

	@Override
	public byte getSerializerAlgorithm() {
		return SerializerAlgorithmEnum.JSON.getType();
	}

	@Override
	public byte[] serialize(Object object) {
		return JSONObject.toJSONBytes(object);
	}

	@Override
	public <T> T deserialize(Class<T> clazz, byte[] bytes) {
		return JSONObject.parseObject(bytes, clazz);
	}

}
