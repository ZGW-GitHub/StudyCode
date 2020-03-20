/*
      Date:  2019-11-09 13:23
                                 */
package e.proxy.cglibProxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class Test {
    public static void main(String[] args) {

        TanK proxy = (TanK) Enhancer.create(TanK.class, new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                System.out.println(System.currentTimeMillis());
                method.invoke(new TanK(), args);
                System.out.println(System.currentTimeMillis());
                return null;
            }
        });

        proxy.run();

        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
