package com.code.jvm;

import org.junit.Test;

import java.lang.ref.PhantomReference;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * Java 四种应用
 *
 * @author 愆凡
 * @date 2021/3/29 16:15
 */
public class ReferenceTest {

	/**
	 * 强引用
	 */
	@Test
	public void strongReferenceTest() {

	}

	/**
	 * 软引用<br />
	 *
	 * JDK 1.2 后提供了 SoftReference 类来实现软引用
	 */
	@Test
	public void softReferenceTest() {
		Object obj = new Object();

		// 这时候 sf 是对 obj 的一个软引用
		SoftReference<Object> sf = new SoftReference<>(obj);

		obj = null;

		// 通过 sf.get() 方法可以取到 obj 对象；
		// 但是，当 obj 对象被标记为需要回收的对象时，则返回 null
		Object result = sf.get();

		boolean enqueued = sf.isEnqueued();

		System.err.println(result);
		System.err.println(enqueued);
	}

	/**
	 * 弱引用<br />
	 *
	 * JDK 1.2 后提供了 WeakReference 类来实现弱引用
	 */
	@Test
	public void weakReferenceTest() {
		Object obj = new Object();

		WeakReference<Object> wf = new WeakReference<>(obj);

		obj = null;

		// 有时候会返回 null
		Object result = wf.get();

		boolean enqueued = wf.isEnqueued();

		System.err.println(result);
		System.err.println(enqueued);
	}

	/**
	 * 虚引用<br />
	 *
	 * JDK 1.2 后提供了 PhantomReference 类来实现虚引用
	 */
	@Test
	public void strongTest() {
		Object obj = new Object();

		PhantomReference<Object> pf = new PhantomReference<>(obj, null);

		obj = null;

		// 永远返回 null
		Object result = pf.get();
		
		boolean enqueued = pf.isEnqueued();

		System.err.println(result);
		System.err.println(enqueued);
	}

}
