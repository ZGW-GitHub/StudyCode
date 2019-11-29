/*
      Date:  2019-11-13 23:01
                                 */
package com.snow.study.Netty.w3cschool.d_time;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

public class TimeDecoder extends ReplayingDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        if (in.readableBytes() < 4) {
            return;
        }
        out.add(new MyTime(in.readUnsignedInt()));
    }
}
