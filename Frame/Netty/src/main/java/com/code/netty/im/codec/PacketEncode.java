package com.code.netty.im.codec;

import com.code.netty.im.protocol.Packet;
import com.code.netty.im.protocol.PacketCodec;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author 愆凡
 * @date 2021/5/6 22:50
 */
public class PacketEncode extends MessageToByteEncoder<Packet> {

	@Override
	protected void encode(ChannelHandlerContext ctx, Packet packet, ByteBuf byteBuf) {
		PacketCodec.encode(byteBuf, packet);
	}

}
