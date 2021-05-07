package com.code.netty.im.utils;

import com.code.netty.im.protocol.attribute.AttributeEnum;
import io.netty.channel.Channel;
import io.netty.util.Attribute;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 愆凡
 * @date 2021/5/6 21:15
 */
@Slf4j
public class LoginUtil {

	public static void markAsLogin(Channel channel) {
		log.info("标记用户已登录");
		channel.attr(AttributeEnum.LOGIN_FLAG.getAttributeKey()).set(true);
	}

	public static boolean hasLogin(Channel channel) {
		log.info("校验用户是否已经登录");

		Attribute<Object> loginAttr = channel.attr(AttributeEnum.LOGIN_FLAG.getAttributeKey());

		return loginAttr.get() != null;
	}

}
