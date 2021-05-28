package com.code.netty.nn.rpc.remoting.client.handler;

import com.code.netty.nn.rpc.remoting.protocol.request.HeartBeatRequestPacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author 愆凡
 * @date 2021/5/12 11:07
 */
@Slf4j
public class HeartBeatTimerHandler extends ChannelInboundHandlerAdapter {

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		ctx.executor().scheduleAtFixedRate(() -> {

			if (ctx.channel().isActive()) {
				log.debug(ctx.channel().toString() + "：发送心跳请求包");
				ctx.writeAndFlush(new HeartBeatRequestPacket());
			}

		}, 5, 5, TimeUnit.SECONDS);

		super.channelActive(ctx);
	}

}
