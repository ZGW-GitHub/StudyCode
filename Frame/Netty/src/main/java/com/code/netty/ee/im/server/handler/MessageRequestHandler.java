package com.code.netty.ee.im.server.handler;

import com.code.netty.ee.im.protocol.request.MessageRequestPacket;
import com.code.netty.ee.im.protocol.response.MessageResponsePacket;
import com.code.netty.ee.im.server.session.Session;
import com.code.netty.ee.im.server.session.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 愆凡
 * @date 2021/5/6 22:46
 */
@Slf4j
@Sharable
public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {

	public static final MessageRequestHandler INSTANCE = new MessageRequestHandler();

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket messageRequestPacket) {
		// 获取消息发送方的会话信息
		Session session = SessionUtil.getSession(ctx.channel());
		if (session.getUserid().equals(messageRequestPacket.getToUserid())) {
			log.warn("不允许发送消息给自己！");
			return;
		}

		System.err.println("收到[" + session.getUserid() + "]发给[" + messageRequestPacket.getToUserid() + "]的消息: " + messageRequestPacket.getMessage());

		// 组装 Response
		MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
		messageResponsePacket.setFromUserid(session.getUserid());
		messageResponsePacket.setFromUserName(session.getUserName());
		messageResponsePacket.setMessage(messageRequestPacket.getMessage());

		// 获取消息接收方的 Channel
		Channel toUserChannel = SessionUtil.getChannelByUserid(messageRequestPacket.getToUserid());

		// 将 Response 写入 Channel
		if (toUserChannel != null && SessionUtil.hasLogin(toUserChannel)) {
			toUserChannel.writeAndFlush(messageResponsePacket);
		} else {
			System.err.println("[" + messageRequestPacket.getToUserid() + "] 不在线，发送失败！");
		}
	}

}
