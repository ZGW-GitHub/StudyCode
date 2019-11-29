/*
      Date:  2019-11-13 23:00
                                 */
package com.snow.study.Netty.w3cschool.d_time;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TimeClientChannelHandler extends ChannelInboundHandlerAdapter {

    Logger logger = LoggerFactory.getLogger(TimeClientChannelHandler.class);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        logger.info("---" + ((MyTime) msg).toString() + "---");
        ctx.close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
