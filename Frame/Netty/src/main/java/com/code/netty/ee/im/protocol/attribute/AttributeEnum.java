package com.code.netty.ee.im.protocol.attribute;

import io.netty.util.AttributeKey;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author 愆凡
 * @date 2021/5/6 21:13
 */
@Getter
@AllArgsConstructor
public enum AttributeEnum {

	/**
	 * 用户登录信息
	 */
	SESSION(AttributeKey.newInstance("session"));

	private final AttributeKey<Object> attributeKey;

}
