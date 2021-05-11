package com.code.netty.im.server.handler;

import com.code.netty.im.server.session.SessionUtil;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 愆凡
 * @date 2021/5/7 21:48
 */
@Slf4j
@Sharable
public class AuthHandler extends SimpleChannelInboundHandler<Object> {

	public static final AuthHandler INSTANCE = new AuthHandler();
	
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Object msg) {
		if (!SessionUtil.hasLogin(ctx.channel())) {
			log.debug("用户没有登录，直接关闭连接！");

			ctx.channel().close();
		} else {
			log.debug("用户已登录，将 AuthHandler 从 Handler 链中移除！");

			ctx.pipeline().remove(this);
		}
	}

}
