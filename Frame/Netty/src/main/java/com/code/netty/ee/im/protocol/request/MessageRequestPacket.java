package com.code.netty.ee.im.protocol.request;

import com.code.netty.ee.im.protocol.Packet;
import com.code.netty.ee.im.protocol.PacketEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author 愆凡
 * @date 2021/5/6 20:56
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class MessageRequestPacket extends Packet {

	private String toUserid;
	private String message;

	@Override
	public Byte getType() {
		return PacketEnum.MESSAGE_REQUEST.getType();
	}

}

