package com.code.dubbo.api.filter;

import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;

/**
 * @author 愆凡
 * @date 2021/5/22 16:45
 */
@Activate(group = {"dubbo-demo"}, order = -1000)
public class DemoFilter implements Filter {

	@Override
	public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
		System.err.println("before filter ...");

		Result result = invoker.invoke(invocation);
		System.err.println("filter result : " + result);

		System.err.println("after filter ...");

		return result;
	}

}
