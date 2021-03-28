package com.code.spring.zu.data.binding;

import com.code.spring.MySpringApplicationTest;
import org.junit.Test;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.util.stream.Stream;

/**
 * @author 愆凡
 * @date 2021/3/28 22:03
 */
public class JavaBeansTest extends MySpringApplicationTest {

	@Test
	public void test() throws IntrospectionException {
		BeanInfo beanInfo = Introspector.getBeanInfo(DataBinderUser.class);

		Stream.of(beanInfo.getPropertyDescriptors()).forEach(System.err::println);

		System.out.println("===================================================");

		Stream.of(beanInfo.getMethodDescriptors()).forEach(System.err::println);
	}

}
