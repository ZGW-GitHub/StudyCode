package com.code.thread.oo.atomic;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author 愆凡
 */
public class AtomicBooleanTest {

	@Test
	public void test() {
		AtomicBoolean boo = new AtomicBoolean(true);

		boo.lazySet(false);

		System.out.println(boo);
		System.out.println(boo);
	}

}
