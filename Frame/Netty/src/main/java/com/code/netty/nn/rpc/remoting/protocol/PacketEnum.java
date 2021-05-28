package com.code.netty.nn.rpc.remoting.protocol;

import com.code.netty.nn.rpc.remoting.protocol.request.HeartBeatRequestPacket;
import com.code.netty.nn.rpc.remoting.protocol.request.MessageRequestPacket;
import com.code.netty.nn.rpc.remoting.protocol.response.HeartBeatResponsePacket;
import com.code.netty.nn.rpc.remoting.protocol.response.MessageResponsePacket;
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
	 * 消息请求包
	 */
	MESSAGE_REQUEST((byte) 3, MessageRequestPacket.class),
	/**
	 * 消息响应包
	 */
	MESSAGE_RESPONSE((byte) 4, MessageResponsePacket.class),
	/**
	 * 心跳请求包
	 */
	HEART_BEAT_REQUEST((byte) 9, HeartBeatRequestPacket.class),
	/**
	 * 心跳响应包
	 */
	HEART_BEAT_RESPONSE((byte) 10, HeartBeatResponsePacket.class);

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
