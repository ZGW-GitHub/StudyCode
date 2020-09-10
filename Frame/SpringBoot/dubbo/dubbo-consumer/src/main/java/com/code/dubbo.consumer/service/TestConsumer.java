package com.code.dubbo.consumer.service;

import com.code.dubbo.api.TestService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Component;

/**
 * @author NotUpToYou
 */
@Component
public class TestConsumer {

    /**
     * 调用服务
     */
    @Reference
    private TestService testService;

    public String test(String msg) {
        return testService.test(msg);
    }

}
