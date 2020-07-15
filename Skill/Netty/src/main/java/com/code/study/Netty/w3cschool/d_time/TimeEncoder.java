/*
      Date:  2019-11-13 23:01
                                 */
package com.code.study.Netty.w3cschool.d_time;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class TimeEncoder extends MessageToByteEncoder<MyTime> {
    @Override
    protected void encode(ChannelHandlerContext ctx, MyTime msg, ByteBuf out) throws Exception {
        out.writeInt((int) msg.getTime());
    }
}
