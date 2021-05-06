package com.code.netty.im.utils;

import com.code.netty.im.protocol.attribute.AttributeEnum;
import io.netty.channel.Channel;
import io.netty.util.Attribute;

/**
 * @author 愆凡
 * @date 2021/5/6 21:15
 */
public class LoginUtil {

	public static void markAsLogin(Channel channel) {
		channel.attr(AttributeEnum.LOGIN_FLAG.getAttributeKey()).set(true);
	}

	public static boolean hasLogin(Channel channel) {
		Attribute<Object> loginAttr = channel.attr(AttributeEnum.LOGIN_FLAG.getAttributeKey());

		return loginAttr.get() != null;
	}

}
