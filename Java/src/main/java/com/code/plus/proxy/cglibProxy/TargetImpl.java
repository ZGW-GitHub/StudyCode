package com.code.plus.proxy.cglibProxy;

import java.util.Random;

/**
 * @author 愆凡
 */
public class TargetImpl {

	public Boolean run() throws InterruptedException {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();

		System.err.println("目标类 " + methodName + " 方法被执行.");

		Thread.sleep(new Random().nextInt(5000));

		return false;
	}

}
