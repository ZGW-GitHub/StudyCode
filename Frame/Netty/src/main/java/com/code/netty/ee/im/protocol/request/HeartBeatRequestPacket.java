package com.code.netty.ee.im.protocol.request;

import com.code.netty.ee.im.protocol.Packet;
import com.code.netty.ee.im.protocol.PacketEnum;

/**
 * @author 愆凡
 * @date 2021/5/12 11:09
 */
public class HeartBeatRequestPacket extends Packet {

	@Override
	public Byte getType() {
		return PacketEnum.HEART_BEAT_REQUEST.getType();
	}

}
