package com.code.netty.im.protocol;

import com.code.netty.im.protocol.request.CreateGroupRequestPacket;
import com.code.netty.im.protocol.request.JoinGroupRequestPacket;
import com.code.netty.im.protocol.request.LoginRequestPacket;
import com.code.netty.im.protocol.request.MessageRequestPacket;
import com.code.netty.im.protocol.response.CreateGroupResponsePacket;
import com.code.netty.im.protocol.response.JoinGroupResponsePacket;
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
public enum PacketEnum {

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
	MESSAGE_RESPONSE((byte) 4, MessageResponsePacket.class),
	/**
	 * 创建群聊请求包
	 */
	CREATE_GROUP_REQUEST((byte) 5, CreateGroupRequestPacket.class),
	/**
	 * 创建群聊响应包
	 */
	CREATE_GROUP_RESPONSE((byte) 6, CreateGroupResponsePacket.class),
	/**
	 * 加入群聊请求包
	 */
	JOIN_GROUP_REQUEST((byte) 7, JoinGroupRequestPacket.class),
	/**
	 * 加入群聊响应包
	 */
	JOIN_GROUP_RESPONSE((byte) 8, JoinGroupResponsePacket.class);

	private final Byte type;
	private final Class<? extends Packet> packet;

	public static Class<? extends Packet> getPacket(Byte type) {
		PacketEnum[] enums = PacketEnum.values();
		for (PacketEnum e : enums) {
			if (e.getType().equals(type)) {
				return e.getPacket();
			}
		}

		throw new RuntimeException("没有找到对应的协议");
	}

}
