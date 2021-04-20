package com.code.netty.demo.heartbeat;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.concurrent.EventExecutorGroup;

/**
 * TODO
 *
 * @author 愆凡
 * @date 2021/4/20 11:21
 */
public class HeartbeatServerHandler extends ChannelInboundHandlerAdapter {

	/**
	 * @param ctx 上下文
	 * @param evt 事件
	 */
	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) {
		if (evt instanceof IdleStateEvent) {
			IdleStateEvent event = (IdleStateEvent) evt;

			String eventType = null;
			switch (event.state()) {
				case READER_IDLE:
					eventType = "读空闲";
					break;
				case WRITER_IDLE:
					eventType = "写空闲";
					break;
				case ALL_IDLE:
					eventType = "读写空闲";
					break;
				default:
					break;
			}

			System.err.println(ctx.channel().remoteAddress() + " --超时时间-- " + eventType);
			System.err.println("服务器做相应处理...");

			// ctx.channel().close(); // 如果发生空闲，我们关闭通道
		}
	}
}
