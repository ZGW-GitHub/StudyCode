package com.code.collection.list.arraylist;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

/**
 * 循环删除 ArrayList 中指定元素
 *
 * @author 愆凡
 * @date 2020/7/17 4:21 下午
 */
@SuppressWarnings("all")
public class RemoveDemo {

	private final List<String> STRING_LIST = new ArrayList<>();

	private final Predicate<String> predicate = p -> "a".equals(p) || "b".equals(p);

	@Before
	public void init() {
		STRING_LIST.add("a");
		STRING_LIST.add("b");
		STRING_LIST.add("c");
	}

	@After
	public void destroy() {
		System.out.println(STRING_LIST.toString());
	}

	/**
	 * 使用 removeIf() 底层使用的是迭代器循环
	 *
	 * 正确
	 */
	@Test
	public void deleteByMethod() {
		STRING_LIST.removeIf(predicate);
	}

	/**
	 * 迭代器循环
	 *
	 * 正确
	 */
	@Test
	public void deleteByIterator() {
		Iterator<String> iterator = STRING_LIST.iterator();

		while (iterator.hasNext()) {
			if (predicate.test(iterator.next())) {
				iterator.remove();
			}
		}
	}

	/**
	 * 普通for循环倒序删除
	 *
	 * 正确
	 */
	@Test
	public void deleteByReverseOrder() {
		for (int i = STRING_LIST.size() - 1; i >= 0; i--) {
			if (predicate.test(STRING_LIST.get(i))) {
				STRING_LIST.remove(STRING_LIST.get(i));
			}
		}
	}

	/**
	 * 调用批量删除方法
	 *
	 * 正确
	 */
	@Test
	public void deleteAll() {
		List<String> removeList = new ArrayList<>();

		for (String string : STRING_LIST) {
			if (predicate.test(string)) {
				removeList.add(string);
			}
		}

		STRING_LIST.removeAll(removeList);
	}

	/**
	 * 迭代器循环，使用 ArrayList 的 remove() 方法删除
	 *
	 * 结果不正确
	 */
	@Test
	public void deleteByArrayList() {
		Iterator<String> iterator = STRING_LIST.iterator();

		while (iterator.hasNext()) {
			if (predicate.test(iterator.next())) {
				STRING_LIST.remove(iterator.next());
			}
		}
	}

	/**
	 * 普通for循环正序删除
	 *
	 * 结果不正确
	 */
	@Test
	public void deleteByOrder() {
		for (int i = 0; i < STRING_LIST.size(); i++) {
			if (predicate.test(STRING_LIST.get(i))) {
				STRING_LIST.remove(STRING_LIST.get(i));
			}
		}
	}

	/**
	 * java8 forEach 方法删除
	 *
	 * 不能删除 报错 java.util.ConcurrentModificationException
	 */
	@Test
	public void deleteByForeach() {
		STRING_LIST.forEach(p -> {
			if (predicate.test(p)) {
				STRING_LIST.remove(p);
			}
		});
	}

	/**
	 * 增强版for循环删除
	 *
	 * 不能删除 报错 java.util.ConcurrentModificationException
	 */
	@Test
	public void deleteByEnhancedForLoop() {
		for (String string : STRING_LIST) {
			if (predicate.test(string)) {
				STRING_LIST.remove(string);
			}
		}
	}

}
