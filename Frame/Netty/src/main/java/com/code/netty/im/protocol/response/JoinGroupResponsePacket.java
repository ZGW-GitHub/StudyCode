package com.code.netty.im.protocol.response;

import com.code.netty.im.protocol.Packet;
import com.code.netty.im.protocol.PacketEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author 愆凡
 * @date 2021/5/11 09:42
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class JoinGroupResponsePacket extends Packet {

	private List<String> userNameList;

	private boolean success;

	private String reason;

	@Override
	public Byte getType() {
		return PacketEnum.JOIN_GROUP_RESPONSE.getType();
	}

}
