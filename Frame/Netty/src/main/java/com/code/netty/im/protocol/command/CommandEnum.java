package com.code.netty.im.protocol.command;

import com.code.netty.im.protocol.Packet;
import com.code.netty.im.protocol.request.LoginRequestPacket;
import com.code.netty.im.protocol.request.MessageRequestPacket;
import com.code.netty.im.protocol.response.LoginResponsePacket;
import com.code.netty.im.protocol.response.MessageResponsePacket;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author 愆凡
 * @date 2021/5/5 22:07
 */
@Getter
@AllArgsConstructor
public enum CommandEnum {

	/**
	 * 登录请求包
	 */
	LOGIN_REQUEST((byte) 1, LoginRequestPacket.class),
	/**
	 * 登录响应包
	 */
	LOGIN_RESPONSE((byte) 2, LoginResponsePacket.class),
	/**
	 * 消息请求包
	 */
	MESSAGE_REQUEST((byte) 3, MessageRequestPacket.class),
	/**
	 * 登录响应包
	 */
	MESSAGE_RESPONSE((byte) 4, MessageResponsePacket.class);

	private final Byte type;
	private final Class<? extends Packet> packet;

	public static Class<? extends Packet> getPacket(Byte type) {
		CommandEnum[] enums = CommandEnum.values();
		for (CommandEnum e : enums) {
			if (e.getType().equals(type)) {
				return e.getPacket();
			}
		}

		throw new RuntimeException("没有找到对应的协议");
	}

}
