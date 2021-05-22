package com.code.dubbo.api.listener.invoker;

import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.InvokerListener;
import org.apache.dubbo.rpc.RpcException;

/**
 * @author 愆凡
 * @date 2021/5/22 18:23
 */
@Activate(order = -1000)
public class DemoInvokerListener implements InvokerListener {
	@Override
	public void referred(Invoker<?> invoker) throws RpcException {
		System.err.println("DemoInvokerListener-referred : " + invoker);
	}

	@Override
	public void destroyed(Invoker<?> invoker) {
		System.err.println("DemoInvokerListener-destroyed : " + invoker);
	}
}
