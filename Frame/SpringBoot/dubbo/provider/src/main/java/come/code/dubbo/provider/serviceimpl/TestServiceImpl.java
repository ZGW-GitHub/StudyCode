/*
      Date:  2019-12-17 18:33
                                 */
package come.code.dubbo.provider.serviceimpl;

import com.code.dubbo.api.TestService;
import org.apache.dubbo.config.annotation.Service;
import org.apache.dubbo.rpc.RpcContext;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author NotUpToYou
 *
 * 暴露服务
 */
@Service
public class TestServiceImpl implements TestService {

    @Override
    public String test(String msg) {

        String time = new SimpleDateFormat("HH:mm:ss").format(new Date());
        System.out.println("[" + time + "] Get : " + msg + ", request from consumer: " + RpcContext.getContext().getRemoteAddress());
        return msg;

    }

}
