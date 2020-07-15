/*
      Date:  2019-11-13 18:04
                                 */
package com.code.study.Netty.w3cschool.c_time;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class TimeServerChannelHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        ChannelFuture channelFuture = ctx.writeAndFlush(ctx.alloc().buffer(4).writeInt((int) (System.currentTimeMillis() / 1000L + 2208988800L)));
        channelFuture.addListener(ChannelFutureListener.CLOSE);

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
