package com.code.netty.im.server.session;

import com.code.netty.im.protocol.attribute.AttributeEnum;
import com.code.netty.im.protocol.response.LoginResponsePacket;
import io.netty.channel.Channel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 愆凡
 * @date 2021/5/8 14:40
 */
public class SessionUtil {

	// key：userid 、value：channel
	public static final Map<String, Channel> USER_ID_CHANNEL_MAP = new ConcurrentHashMap<>();

	/**
	 * 将 Session 与 Channel 关联起来，并将 Session 设置为 Channel 的 session 属性
	 *
	 * @param session s
	 * @param channel c
	 */
	public static void bindSession(Session session, Channel channel) {
		USER_ID_CHANNEL_MAP.put(session.getUserid(), channel);

		channel.attr(AttributeEnum.SESSION.getAttributeKey()).set(session);
	}

	/**
	 * 解除 Session 与 Channel 的关联，并将 Channel 的 session 属性设为 null
	 *
	 * @param channel c
	 */
	public static void unBindSession(Channel channel) {
		if (hasLogin(channel)) {
			USER_ID_CHANNEL_MAP.remove(getSession(channel).getUserid());

			channel.attr(AttributeEnum.SESSION.getAttributeKey()).set(null);
		}
	}

	public static void clientLoginFlag(Channel channel, LoginResponsePacket loginResponsePacket) {
		channel.attr(AttributeEnum.SESSION.getAttributeKey()).set(new Session(loginResponsePacket.getUserid(), ""));
	}

	public static boolean hasLogin(Channel channel) {
		return channel.hasAttr(AttributeEnum.SESSION.getAttributeKey());
	}

	public static Session getSession(Channel channel) {
		return (Session) channel.attr(AttributeEnum.SESSION.getAttributeKey()).get();
	}

	public static Channel getChannelByUserid(String userid) {
		return USER_ID_CHANNEL_MAP.get(userid);
	}

}
