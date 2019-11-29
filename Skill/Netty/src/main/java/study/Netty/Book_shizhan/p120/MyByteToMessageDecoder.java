/*
      Date:  2019-11-21 20:54
                                 */
package com.snow.study.Netty.Book_shizhan.p120;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.TooLongFrameException;

import java.util.List;

public class MyByteToMessageDecoder extends ByteToMessageDecoder {

    // 设置要产生帧最大允许的长度
    private final int maxSize;

    public MyByteToMessageDecoder(int maxSize) {
        if (maxSize <= 0) {
            throw new RuntimeException();
        }
        this.maxSize = maxSize;
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        int canRead = in.readableBytes();
        // 如果消息过长，则丢弃并抛异常
        if (canRead > maxSize) {
            in.clear();
            throw new TooLongFrameException();
        }
        out.add(in.readBytes(canRead));

    }

}
