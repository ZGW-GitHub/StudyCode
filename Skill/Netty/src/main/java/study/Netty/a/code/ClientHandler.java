/*
      Date:  2019-08-08 17:02    
                                 */
package com.snow.study.Netty.a.code;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        BookMessage.Book book = BookMessage.Book.newBuilder().setId(1).setName("JAVA 从入门到精通。。。").build();
        ctx.writeAndFlush(book);

    }
}
