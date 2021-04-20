package com.code.netty.demo.websocket;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.time.LocalDateTime;

/**
 * {@link TextWebSocketFrame}：表示一个文本帧(frame)
 * @author 愆凡
 * @date 2021/4/20 13:34
 */
public class WebSocketTextFrameHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) {
		System.out.println("服务器收到消息 " + msg.text());

		// 回复消息
		ctx.channel().writeAndFlush(new TextWebSocketFrame("服务器时间" + LocalDateTime.now() + " " + msg.text()));
	}

	// 当 web 客户端连接后， 触发方法
	@Override
	public void handlerAdded(ChannelHandlerContext ctx) {
		// id 表示唯一的值，LongText 是唯一的 ShortText 不是唯一
		System.out.println("handlerAdded 被调用" + ctx.channel().id().asLongText());
		System.out.println("handlerAdded 被调用" + ctx.channel().id().asShortText());
	}

	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) {
		System.out.println("handlerRemoved 被调用" + ctx.channel().id().asLongText());
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		System.out.println("异常发生 " + cause.getMessage());
		ctx.close(); //关闭连接
	}

}
