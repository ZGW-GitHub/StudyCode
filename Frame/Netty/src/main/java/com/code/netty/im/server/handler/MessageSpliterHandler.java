package com.code.netty.im.server.handler;

import com.code.netty.im.protocol.Packet;
import com.code.netty.im.protocol.PacketEnum;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * 对于平行的 Handler ，可以通过以下方式聚合分流，以防止 Handler 拦截到与它无关的请求(可以提高性能)<br/>
 * 所谓平行的 Handler 比如：messageHandler 与 createGroupHandler
 * 
 * @author 愆凡
 * @date 2021/5/11 11:53
 */
@Sharable
public class MessageSpliterHandler extends SimpleChannelInboundHandler<Packet> {

	public static final MessageSpliterHandler INSTANCE = new MessageSpliterHandler();

	private final Map<Byte, SimpleChannelInboundHandler<? extends Packet>> handlerMap;

	private MessageSpliterHandler() {
		handlerMap = new HashMap<>();

		handlerMap.put(PacketEnum.MESSAGE_REQUEST.getType(), MessageRequestHandler.INSTANCE);
		handlerMap.put(PacketEnum.CREATE_GROUP_REQUEST.getType(), CreateGroupRequestHandler.INSTANCE);
		handlerMap.put(PacketEnum.JOIN_GROUP_REQUEST.getType(), JoinGroupRequestHandler.INSTANCE);
//		handlerMap.put(PacketEnum.QUIT_GROUP_REQUEST.getType(), QuitGroupRequestHandler.INSTANCE);
//		handlerMap.put(PacketEnum.LIST_GROUP_MEMBERS_REQUEST.getType(), ListGroupMembersRequestHandler.INSTANCE);
//		handlerMap.put(PacketEnum.GROUP_MESSAGE_REQUEST.getType(), GroupMessageRequestHandler.INSTANCE);
//		handlerMap.put(PacketEnum.LOGOUT_REQUEST.getType(), LogoutRequestHandler.INSTANCE);
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Packet packet) throws Exception {
		handlerMap.get(packet.getType()).channelRead(ctx, packet);
	}
	
}
