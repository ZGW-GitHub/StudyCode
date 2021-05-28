package com.code.netty.nn.rpc.remoting.codec;

import com.code.netty.nn.rpc.remoting.protocol.Packet;
import com.code.netty.nn.rpc.remoting.protocol.PacketCodec;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;

import java.util.List;

/**
 * @author 愆凡
 * @date 2021/5/11 11:40
 */
@Sharable
public class PacketCodecHandler extends MessageToMessageCodec<ByteBuf, Packet> {

	public static final PacketCodecHandler INSTANCE = new PacketCodecHandler();

	@Override
	protected void encode(ChannelHandlerContext ctx, Packet msg, List<Object> out) {
		ByteBuf byteBuf = ctx.channel().alloc().ioBuffer();
		PacketCodec.encode(byteBuf, msg);
		out.add(byteBuf);
	}

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) {
		out.add(PacketCodec.decode(msg));
	}

}
