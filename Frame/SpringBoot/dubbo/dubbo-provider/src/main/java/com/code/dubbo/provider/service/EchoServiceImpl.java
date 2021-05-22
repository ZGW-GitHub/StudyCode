package com.code.dubbo.provider.service;

import com.code.dubbo.api.service.EchoService;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.rpc.RpcContext;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 暴露服务
 *
 * @author 愆凡
 */
@DubboService(group = "dubbo-demo", filter = "demoFilter")
public class EchoServiceImpl implements EchoService {

	@Override
	public String echo(String msg) {

		String time = new SimpleDateFormat("HH:mm:ss").format(new Date());
		System.out.println("[" + time + "] Get : " + msg + ", request from consumer: " + RpcContext.getContext().getRemoteAddress());
		return msg;

	}

}
