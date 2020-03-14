/*
      Date:  2019-11-14 9:34
                                 */
package com.snow.study.Netty.w3cschool.e_chat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.springframework.util.ObjectUtils;

public class ChatServerChannelHandler extends SimpleChannelInboundHandler<String> {

    public static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel newChannel = ctx.channel();
        for (Channel channel : channelGroup) {
            channel.writeAndFlush("[server] - " + newChannel.remoteAddress() + " 加入\n");
        }
        channelGroup.add(newChannel);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel newChannel = ctx.channel();
        for (Channel channel : channelGroup) {
            channel.writeAndFlush("[server] - " + newChannel.remoteAddress() + " 离开\n");
        }
        channelGroup.remove(newChannel);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        Channel me = ctx.channel();
        for (Channel channel : channelGroup) {
            if (!ObjectUtils.nullSafeEquals(me, channel)) {
                channel.writeAndFlush("[ " + me.remoteAddress() + " ] 说 :" + msg + "\n");
            } else {
                channel.writeAndFlush("[ me ] 说 :" + msg);
            }
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("ChatClient:" + ctx.channel() + "在线");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("ChatClient:" + ctx.channel() + "掉线");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("ChatClient:" + ctx.channel() + "异常");
        cause.printStackTrace();
        ctx.close();
    }
}
