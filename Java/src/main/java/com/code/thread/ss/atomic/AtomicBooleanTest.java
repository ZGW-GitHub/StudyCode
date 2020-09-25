package com.code.thread.ss.atomic;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author NotUpToYou
 */
public class AtomicBooleanTest {

	public static void main(String[] args) {

		AtomicBoolean boo = new AtomicBoolean(true);

		boo.lazySet(false);

		System.out.println(boo);
		System.out.println(boo);

	}

}
