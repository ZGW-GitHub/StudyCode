package com.code.netty.im.protocol.request;

import com.code.netty.im.protocol.Packet;
import com.code.netty.im.protocol.PacketEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author 愆凡
 * @date 2021/5/11 09:41
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class JoinGroupRequestPacket extends Packet {

	/**
	 * 要加入的群聊的id
	 */
	private String joinGroupid;

	@Override
	public Byte getType() {
		return PacketEnum.JOIN_GROUP_RESPONSE.getType();
	}

}
