/*
      Date:  2019-11-21 20:34
                                 */
package study.Netty.Book_shizhan.p119;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.util.List;

public class MyMessageToMessageEncoder extends MessageToMessageEncoder<ByteBuf> {
    @Override
    protected void encode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {
        while (msg.readableBytes() >= 4) {
            out.add(Math.abs(msg.readInt()));
        }
    }
}