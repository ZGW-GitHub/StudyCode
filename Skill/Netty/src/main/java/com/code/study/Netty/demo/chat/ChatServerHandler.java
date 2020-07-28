package com.code.study.Netty.demo.chat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.ArrayList;
import java.util.List;

public class ChatServerHandler extends SimpleChannelInboundHandler<String> {

    private static List<Channel> channels = new ArrayList<Channel>();

    // 进入此 Handler 最开始执行的方法
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println("【 Server 】 : " + channel.remoteAddress().toString() + " 上线");
        channels.add(channel);
    }

    // 离开此 Handler 时执行的方法
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println("【 Server 】 : " + channel.remoteAddress().toString() + " 下线");
        channels.remove(channel);
    }

    // 读取数据方法
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        Channel channel = ctx.channel();
        for (Channel c : channels) {
            if (c != channel) {
                c.writeAndFlush("【 " + channel.remoteAddress().toString().substring(1) +" 】 说 ： " + msg);
            }
        }
    }

}
