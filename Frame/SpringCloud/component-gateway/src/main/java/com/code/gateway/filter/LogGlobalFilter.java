package com.code.gateway.filter;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 自定义全局Filter，用来记录日志
 *
 * @author 愆凡
 * @date 2021/4/11 21:12
 */
@Component
public class LogGlobalFilter implements GlobalFilter, Ordered {

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		System.err.println("ServerWebExchange : " + ToStringBuilder.reflectionToString(exchange));
		System.err.println("GatewayFilterChain : " + ToStringBuilder.reflectionToString(chain));

//		if (new Random().nextBoolean()) {
//			System.err.println("程序错误");
//			return exchange.getResponse().setComplete();
//		}
		return chain.filter(exchange);
	}

	@Override
	public int getOrder() {
		return 0;
	}

}
