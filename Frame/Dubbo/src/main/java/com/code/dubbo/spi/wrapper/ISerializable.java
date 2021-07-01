package com.code.dubbo.spi.wrapper;

import org.apache.dubbo.common.extension.SPI;

/**
 * 序列化接口
 *
 * @author 愆凡
 * @date 2021/7/1 10:39
 */
@SPI
public interface ISerializable {

	String serialize(Object obj);

	<T> T deserialize(Class<T> clazz, String str);

}
