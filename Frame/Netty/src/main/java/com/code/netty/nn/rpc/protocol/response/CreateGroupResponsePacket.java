package com.code.netty.nn.rpc.protocol.response;

import com.code.netty.nn.rpc.protocol.Packet;
import com.code.netty.nn.rpc.protocol.PacketEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author 愆凡
 * @date 2021/5/10 15:42
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CreateGroupResponsePacket extends Packet {

	private String groupid;

	private List<String> userNameList;

	private boolean success;

	private String reason;

	@Override
	public Byte getType() {
		return PacketEnum.CREATE_GROUP_RESPONSE.getType();
	}

}
