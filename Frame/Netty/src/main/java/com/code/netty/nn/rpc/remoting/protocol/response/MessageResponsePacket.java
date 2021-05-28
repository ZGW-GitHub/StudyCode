package com.code.netty.nn.rpc.remoting.protocol.response;

import com.code.netty.nn.rpc.remoting.protocol.Packet;
import com.code.netty.nn.rpc.remoting.protocol.PacketEnum;
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
	public Byte getType() {
		return PacketEnum.MESSAGE_RESPONSE.getType();
	}

}

