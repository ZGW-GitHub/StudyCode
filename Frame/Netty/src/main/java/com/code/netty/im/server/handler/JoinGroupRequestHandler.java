package com.code.netty.im.server.handler;

import com.code.netty.im.protocol.request.JoinGroupRequestPacket;
import com.code.netty.im.protocol.response.JoinGroupResponsePacket;
import com.code.netty.im.server.session.Session;
import com.code.netty.im.server.session.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 愆凡
 * @date 2021/5/11 09:40
 */
@Slf4j
public class JoinGroupRequestHandler extends SimpleChannelInboundHandler<JoinGroupRequestPacket> {

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, JoinGroupRequestPacket msg) {
		JoinGroupResponsePacket response = new JoinGroupResponsePacket();

		String groupid = msg.getJoinGroupid();
		ChannelGroup channelGroup = SessionUtil.getChannelGroupByid(groupid);
		if (channelGroup == null) {
			log.warn("群聊不存在");

			response.setSuccess(false);
			response.setReason("群聊不存在");
		} else {
			channelGroup.add(ctx.channel());
			
			response.setSuccess(true);
			List<String> userNameList = channelGroup.stream().map(SessionUtil::getSession).map(Session::getUserName).collect(Collectors.toList());
			response.setUserNameList(userNameList);
		}

		ctx.channel().writeAndFlush(response);
	}

}
