package com.code.netty.im.protocol;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author 愆凡
 * @date 2021/5/5 22:07
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class LoginRequestPacket extends Packet {

	private Integer userId;

	private String username;

	private String password;

	@Override
	public Byte getCommand() {
		return CommandEnum.LOGIN_REQUEST.getType();
	}

}

