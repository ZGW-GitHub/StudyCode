package com.code.netty.im.protocol.response;

import com.code.netty.im.protocol.Packet;
import com.code.netty.im.protocol.command.CommandEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author 愆凡
 * @date 2021/5/6 20:56
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class MessageResponsePacket extends Packet {

	private String fromUserid;
	private String fromUserName;
	private String message;

	@Override
	public Byte getCommand() {
		return CommandEnum.MESSAGE_RESPONSE.getType();
	}

}

