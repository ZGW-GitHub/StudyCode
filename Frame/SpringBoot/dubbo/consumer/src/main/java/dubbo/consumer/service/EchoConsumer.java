/*
      Date:  2019-12-17 17:49
                                 */
package dubbo.consumer.service;

import dubbo.api.service.EchoService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Component;

/**
 * @author NotUpToYou
 */
@Component
public class EchoConsumer {

    /**
     * 调用服务
     */
    @Reference
    private EchoService echoService;

    public String echo(String msg) {
        return echoService.echo(msg);
    }

}
