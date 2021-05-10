package com.code.netty.im.protocol.request;

import com.code.netty.im.protocol.Packet;
import com.code.netty.im.protocol.command.CommandEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author 愆凡
 * @date 2021/5/10 15:40
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CreateGroupRequestPacket extends Packet {
	
	private List<String> useridList;

	@Override
	public Byte getCommand() {
		return CommandEnum.CREATE_GROUP_REQUEST.getType();
	}
	
}
