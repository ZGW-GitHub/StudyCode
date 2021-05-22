package com.code.dubbo.consumer.service;

import com.code.dubbo.api.service.TestService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Component;

/**
 * @author 愆凡
 */
@Component
public class TestConsumer {

	/**
	 * 调用服务
	 */
	@DubboReference(group = "dubbo-demo", filter = "demoFilter")
	private TestService testService;

	public String test(String msg) {
		return testService.test(msg);
	}

}
