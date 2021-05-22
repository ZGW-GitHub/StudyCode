package com.code.dubbo.consumer.service;

import com.code.dubbo.api.service.EchoService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Component;

/**
 * @author 愆凡
 */
@Component
public class EchoConsumer {

	/**
	 * 调用服务
	 */
	@DubboReference(group = "dubbo-demo", filter = "demoFilter")
	private EchoService echoService;

	public String echo(String msg) {
		return echoService.echo(msg);
	}

}
