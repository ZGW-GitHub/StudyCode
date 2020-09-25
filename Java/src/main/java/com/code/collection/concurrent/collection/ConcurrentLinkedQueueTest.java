/*
      Date:  2019-08-06 7:41
                                 */
package com.code.collection.concurrent.collection;

import java.util.concurrent.ConcurrentLinkedQueue;

public class ConcurrentLinkedQueueTest {

	public static void main(String[] args) {

		ConcurrentLinkedQueue<Object> queue = new ConcurrentLinkedQueue<>();

		for (int i = 0; i < 100000; i++) {
			queue.add(System.nanoTime());
		}

		System.out.println("放入完成。");

		long millis = System.currentTimeMillis();

        /*while (queue.size() > 0) {
            queue.poll(); // 13758 ms
        }*/

		while (!queue.isEmpty()) {
			queue.poll(); // 4 ms       因为：isEmpty 是判断头是否有元素，size 是遍历计算 queue 大小
		}

		System.out.println(System.currentTimeMillis() - millis);

	}

	// 判断字符串是否存在
	private static void handText(String s) {

		if (null != s && "".equals(s)) {
			// 低效（ 底层使用的循环 ）
		}
		if (null != s && s.length() > 0) {
			// 高效
		}
		if (null != s && !s.isEmpty()) {
			// 高效（ 底层调用的 s.length() ）
		}

	}

}
