/*
      Date:  2019-11-13 18:33
                                 */
package study.Netty.w3cschool.c_time;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Date;

public class TimeClientChannelHandler extends ChannelInboundHandlerAdapter {

    Logger logger = LoggerFactory.getLogger(TimeClientChannelHandler.class);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        long time = (((ByteBuf) msg).readUnsignedInt() - 2208988800L) * 1000L;
        logger.info("---" + new Date(time).toString() + "---");
        ctx.close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
