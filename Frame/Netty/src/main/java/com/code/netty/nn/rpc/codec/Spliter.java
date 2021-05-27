package com.code.netty.nn.rpc.codec;

import com.code.netty.nn.rpc.protocol.Packet;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import lombok.extern.slf4j.Slf4j;

/**
 * 拆包器，用于解决粘包
 *
 * @see LengthFieldBasedFrameDecoder 基于长度域的拆包器：LengthFieldBasedFrameDecoder
 *
 * @author 愆凡
 * @date 2021/5/7 20:38
 */
@Slf4j
public class Spliter extends LengthFieldBasedFrameDecoder {

	public static final int LENGTH_FIELD_OFFSET = 7;
	public static final int LENGTH_FIELD_LENGTH = 4;

	public Spliter() {
		super(Integer.MAX_VALUE, LENGTH_FIELD_OFFSET, LENGTH_FIELD_LENGTH);
	}

	@Override
	protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
		// 屏蔽非本协议的客户端
		if (in.getInt(in.readerIndex()) != Packet.MAGIC_NUMBER) {
			log.error("非本协议的数据包，关闭 channel");

			ctx.channel().close();
			return null;
		}

		return super.decode(ctx, in);
	}

}
