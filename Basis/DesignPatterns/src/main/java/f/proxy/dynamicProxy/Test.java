package f.proxy.dynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author 愆凡
 */
public class Test {

	public static void main(String[] args) {

		Movable tankProxy = (Movable) Proxy.newProxyInstance(
				TanK.class.getClassLoader(),
				TanK.class.getInterfaces(),
				new InvocationHandler() {
					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						System.out.println("开始代理。");

						method.invoke(new TanK(), args);

						System.out.println("结束代理");
						return null;
					}
				});
		tankProxy.run();

	}

}
