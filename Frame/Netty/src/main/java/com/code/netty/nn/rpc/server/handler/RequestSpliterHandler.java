package com.code.netty.nn.rpc.server.handler;

import com.code.netty.nn.rpc.protocol.Packet;
import com.code.netty.nn.rpc.protocol.PacketEnum;
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
public class RequestSpliterHandler extends SimpleChannelInboundHandler<Packet> {

	public static final RequestSpliterHandler INSTANCE = new RequestSpliterHandler();

	private final Map<Byte, SimpleChannelInboundHandler<? extends Packet>> handlerMap = new HashMap<>();

	private RequestSpliterHandler() {
		handlerMap.put(PacketEnum.MESSAGE_REQUEST.getType(), MessageRequestHandler.INSTANCE);
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Packet packet) throws Exception {
		handlerMap.get(packet.getType()).channelRead(ctx, packet);
	}

}
