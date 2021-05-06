package com.code.netty.im.protocol.attribute;

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
	 * 登录状态标识
	 */
	LOGIN_FLAG(AttributeKey.newInstance("login"));

	private final AttributeKey<Object> attributeKey;

}
