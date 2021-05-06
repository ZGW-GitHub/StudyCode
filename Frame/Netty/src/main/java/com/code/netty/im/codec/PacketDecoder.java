package com.code.netty.im.codec;

import com.code.netty.im.protocol.PacketCodec;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @author 愆凡
 * @date 2021/5/6 22:40
 */
public class PacketDecoder extends ByteToMessageDecoder {

	@Override
	protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) {
		list.add(PacketCodec.decode(byteBuf));
	}

}
