package com.code.netty.im.protocol.response;

import com.code.netty.im.protocol.Packet;
import com.code.netty.im.protocol.command.CommandEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author 愆凡
 * @date 2021/5/6 20:40
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class LoginResponsePacket extends Packet {

	private boolean success;

	private String reason;

	@Override
	public Byte getCommand() {
		return CommandEnum.LOGIN_RESPONSE.getType();
	}

}
