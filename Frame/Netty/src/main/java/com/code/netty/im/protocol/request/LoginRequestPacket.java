package com.code.netty.im.protocol.request;

import com.code.netty.im.protocol.Packet;
import com.code.netty.im.protocol.command.CommandEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author 愆凡
 * @date 2021/5/5 22:07
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class LoginRequestPacket extends Packet {

	private String userid;

	private String username;

	private String password;

	@Override
	public Byte getCommand() {
		return CommandEnum.LOGIN_REQUEST.getType();
	}

}

