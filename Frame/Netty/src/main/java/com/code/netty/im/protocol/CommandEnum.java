package com.code.netty.im.protocol;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author 愆凡
 * @date 2021/5/5 22:07
 */
@Getter
@AllArgsConstructor
enum CommandEnum {

	/**
	 * 登录指令
	 */
	LOGIN_REQUEST((byte) 1, LoginRequestPacket.class);

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
