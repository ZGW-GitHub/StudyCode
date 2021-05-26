package com.code.netty.im.protocol.response;

import com.code.netty.im.protocol.Packet;
import com.code.netty.im.protocol.PacketEnum;

/**
 * @author 愆凡
 * @date 2021/5/12 11:15
 */
public class HeartBeatResponsePacket extends Packet {

	@Override
	public Byte getType() {
		return PacketEnum.HEART_BEAT_RESPONSE.getType();
	}

}
