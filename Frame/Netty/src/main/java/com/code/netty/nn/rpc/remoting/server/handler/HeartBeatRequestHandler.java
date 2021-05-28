package com.code.netty.nn.rpc.remoting.server.handler;

import com.code.netty.nn.rpc.remoting.protocol.request.HeartBeatRequestPacket;
import com.code.netty.nn.rpc.remoting.protocol.response.HeartBeatResponsePacket;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 愆凡
 * @date 2021/5/12 11:14
 */
@Slf4j
@Sharable
public class HeartBeatRequestHandler extends SimpleChannelInboundHandler<HeartBeatRequestPacket> {

	public static final HeartBeatRequestHandler INSTANCE = new HeartBeatRequestHandler();

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, HeartBeatRequestPacket requestPacket) {
		log.info(ctx.channel().toString() + "：收到心跳请求包，回复心跳响应包");

		ctx.writeAndFlush(new HeartBeatResponsePacket());
	}

}
