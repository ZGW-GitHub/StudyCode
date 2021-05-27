package com.code.netty.ee.im.protocol.response;

import com.code.netty.ee.im.protocol.Packet;
import com.code.netty.ee.im.protocol.PacketEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author 愆凡
 * @date 2021/5/6 20:40
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class LoginResponsePacket extends Packet {

	private String userid;

	private boolean success;

	private String reason;

	@Override
	public Byte getType() {
		return PacketEnum.LOGIN_RESPONSE.getType();
	}

}
