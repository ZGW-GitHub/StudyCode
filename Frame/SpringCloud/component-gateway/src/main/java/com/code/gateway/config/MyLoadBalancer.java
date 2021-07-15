package com.code.gateway.config;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.Server;
import lombok.extern.slf4j.Slf4j;

/**
 * 负载均衡
 * 
 * @author 愆凡
 * @date 2021/7/6 17:53
 */
@Slf4j
public class MyLoadBalancer extends AbstractLoadBalancerRule {
	@Override
	public void initWithNiwsConfig(IClientConfig iClientConfig) {

	}

	@Override
	public Server choose(Object obj) {
		return null;
	}
}
