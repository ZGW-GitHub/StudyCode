package com.code.study.Netty.a.basic;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

public class NettyClientHandler extends ChannelInboundHandlerAdapter {

    // 通道就绪事件
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        System.out.println("客户端 ChannelHandlerContext ：" + ctx);
        ctx.writeAndFlush(Unpooled.copiedBuffer("我是客户端。。。", CharsetUtil.UTF_8));

    }

    // 读取数据方法
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        System.out.println("服务端发来的消息：" + buf.toString(CharsetUtil.UTF_8));
    }

}
