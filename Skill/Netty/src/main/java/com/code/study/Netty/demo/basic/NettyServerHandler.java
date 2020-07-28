package com.code.study.Netty.demo.basic;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

// 自定义一个服务端业务处理类
public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    // 读取数据方法
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("服务端 ChannelHandlerContext ：" + ctx);
        ByteBuf buf = (ByteBuf) msg;
        System.out.println("接收到消息：" + buf.toString(CharsetUtil.UTF_8));
    }

    // 读取完毕方法
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.copiedBuffer("回应消息。", CharsetUtil.UTF_8));
    }

    // 出异常方法
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
