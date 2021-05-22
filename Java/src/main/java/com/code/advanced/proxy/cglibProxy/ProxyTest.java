package com.code.advanced.proxy.cglibProxy;

import org.junit.Test;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author 愆凡
 */
public class ProxyTest {

	@Test
	public void test() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
		Object proxyResult = doProxy(TargetImpl.class, "run");

		System.err.println(proxyResult);
	}

	@SuppressWarnings("all")
	public Object doProxy(Class targetClass, String targetMethodName) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
		Object tankProxy = Enhancer.create(targetClass, (MethodInterceptor) (o, method, args, methodProxy) -> {
			System.out.println("开始代理");

			// 调用被代理对象的被代理方法
			method.invoke(targetClass.getDeclaredConstructor().newInstance());

			System.out.println("结束代理");

			return true;
		});

		// 调用代理对象的 run 方法
		Method method = tankProxy.getClass().getMethod(targetMethodName);
		return method.invoke(tankProxy);
	}

}
