/*
      Date:  2019-11-21 19:53
                                 */
package study.Netty.Book_shizhan.p116;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * 单元测试入站消息
 */
public class MyByteToMessageDecoder extends ByteToMessageDecoder {

    // 指定帧的长度
    private final int length;

    public MyByteToMessageDecoder(int length) {
        if (length <= 0) {
            throw new RuntimeException();
        }
        this.length = length;
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        // 当入站数据长度大于规定的帧长度，就将该帧添加到已被解码的消息列表中
        if (in.readableBytes() >= length) {
            out.add(in.readBytes(length));
        }
    }
}
