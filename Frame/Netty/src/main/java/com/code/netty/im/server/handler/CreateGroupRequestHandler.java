package com.code.netty.im.server.handler;

import cn.hutool.core.util.StrUtil;
import com.code.netty.im.protocol.request.CreateGroupRequestPacket;
import com.code.netty.im.protocol.response.CreateGroupResponsePacket;
import com.code.netty.im.server.session.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 愆凡
 * @date 2021/5/10 15:45
 */
@Slf4j
public class CreateGroupRequestHandler extends SimpleChannelInboundHandler<CreateGroupRequestPacket> {
	
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, CreateGroupRequestPacket msg) {
		List<String> useridList = msg.getUseridList();
		useridList.add(SessionUtil.getSession(ctx.channel()).getUserid());

		// 1、创建用于保存新群聊相关的 channel 的 group
		final ChannelGroup channelGroup = new DefaultChannelGroup(ctx.executor());
		final String groupid = StrUtil.uuid().substring(32);
		SessionUtil.bindChannelGroup(groupid, channelGroup);

		// 2、将群聊相关用户的 channel 添加到 group 中
		List<String> userNameList = new ArrayList<>(useridList.size());
		useridList.forEach(userid -> {
			Channel channel = SessionUtil.getChannelByUserid(userid);
			if (channel != null) {
				channelGroup.add(channel);
				userNameList.add(SessionUtil.getSession(channel).getUserName());
			}
		});
		
		// 3、创建 response
		CreateGroupResponsePacket responsePacket = new CreateGroupResponsePacket(groupid, userNameList, true, null);
		
		// 4、给每个群成员发送 response
		channelGroup.writeAndFlush(responsePacket);
		
		log.debug("群聊【 " + groupid + " 】创建成功，成员：" + String.join("、", userNameList));
	}
	
}
