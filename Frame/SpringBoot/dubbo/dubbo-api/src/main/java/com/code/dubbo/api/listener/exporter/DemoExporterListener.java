package com.code.dubbo.api.listener.exporter;

import org.apache.dubbo.rpc.Exporter;
import org.apache.dubbo.rpc.ExporterListener;
import org.apache.dubbo.rpc.RpcException;

/**
 * @author 愆凡
 * @date 2021/5/22 18:34
 */
public class DemoExporterListener implements ExporterListener {
	@Override
	public void exported(Exporter<?> exporter) throws RpcException {
		System.err.println("DemoExporterListener-referred : " + exporter.getInvoker().getUrl());
	}

	@Override
	public void unexported(Exporter<?> exporter) {
		System.err.println("DemoExporterListener-unexported : " + exporter.getInvoker().getUrl());
	}
}
