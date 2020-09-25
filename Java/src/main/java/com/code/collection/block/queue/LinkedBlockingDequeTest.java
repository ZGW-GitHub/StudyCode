package com.code.collection.block.queue;

import org.junit.Test;

import java.util.concurrent.LinkedBlockingDeque;

/**
 * Deque ------> Double End Queue
 * <p>
 * 注意：
 * 无参构造：则队列最大为 Integer.MAX_VALUE
 * 有参构造：则队列最大为传入的参数
 * 有参（参数为容器对象）构造：此构造会先调用有参构造：this(Integer.MAX_VALUE);
 * <p>
 * 可以双向存取
 * <p>
 * 不能有NULL元素
 *
 * @author 愆凡
 */
public class LinkedBlockingDequeTest {

	private final LinkedBlockingDeque<Object> deque = new LinkedBlockingDeque<>(2);

	@Test
	public void addTest() {

	}

}
