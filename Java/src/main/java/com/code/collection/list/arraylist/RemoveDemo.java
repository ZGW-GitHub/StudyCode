package com.code.collection.list.arraylist;

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


	public static void main(String[] args) {

		Predicate<String> predicate = p -> "a".equals(p) || "b".equals(p);

		// 迭代器循环，使用迭代器的remove()方法删除
		// 可以正常删除结果正确
		deleteByIterator(getList(), predicate);

		// 迭代器循环，使用迭代器的remove()方法删除
		// 可以正常删除结果正确
		deleteByMethod(getList(), predicate);

		// 普通for循环倒序删除
		// 可以正常删除结果正确
		deleteByReverseOrder(getList(), predicate);

		// 调用批量删除方法
		// 正常删除数据
		deleteAll(getList(), predicate);

		// 普通for循环正序删除
		// 可以删除 结果不正确
		deleteByOrder(getList(), predicate);

		// 迭代器循环，使用ArrayList的remove()方法删除
		// 不能删除 报错java.util.ConcurrentModificationException
		deleteByArrayList(getList(), predicate);

		// java8 forEach方法删除
		// 不能删除 报错java.util.ConcurrentModificationException
		deleteByForeach(getList(), predicate);

		// 增强版for循环删除
		// 不能删除 报错 java.util.ConcurrentModificationException
		deleteByEnhancedForLoop(getList(), predicate);
	}

	public static List<String> getList() {
		List<String> list = new ArrayList<>();
		list.add("a");
		list.add("b");
		list.add("c");
		return list;
	}


	/**
	 * 普通for循环倒序删除
	 * 可以正常删除  结果正确
	 */
	public static void deleteByReverseOrder(List<String> list, Predicate<String> predicate) {
		for (int i = list.size() - 1; i >= 0; i--) {
			if (predicate.test(list.get(i))) {
				list.remove(list.get(i));
			}
		}
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + ":" + list.toString());
	}

	/**
	 * 普通for循环正序删除
	 * 可以删除  结果不正确
	 */
	public static void deleteByOrder(List<String> list, Predicate<String> predicate) {
		for (int i = 0; i < list.size(); i++) {
			if (predicate.test(list.get(i))) {
				list.remove(list.get(i));
			}
		}
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + ":" + list.toString());
	}


	/**
	 * 迭代器循环，使用ArrayList的remove()方法删除
	 * 可以删除  结果不正确
	 */
	public static void deleteByArrayList(List<String> list, Predicate<String> predicate) {
		Iterator<String> iterator = list.iterator();
		while (iterator.hasNext()) {
			if (predicate.test(iterator.next())) {
				list.remove(iterator.next());
			}
		}
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + ":" + list.toString());

	}

	/**
	 * 迭代器循环，使用迭代器的remove()方法删除
	 * 可以正常删除结果正确
	 */
	public static void deleteByIterator(List<String> list, Predicate<String> predicate) {
		Iterator<String> iterator = list.iterator();
		while (iterator.hasNext()) {
			if (predicate.test(iterator.next())) {
				iterator.remove();
			}
		}
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + ":" + list.toString());

	}

	/**
	 * 迭代器循环，使用集合的removeIf()方法删除
	 * 可以正常删除结果正确
	 */
	public static void deleteByMethod(List<String> list, Predicate<String> predicate) {
		list.removeIf(predicate);

		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + ":" + list.toString());
	}

	/**
	 * java8 forEach方法删除
	 * 不能删除 报错 java.util.ConcurrentModificationException
	 */
	public static void deleteByForeach(List<String> list, Predicate<String> predicate) {
		list.forEach(p -> {
			if (predicate.test(p)) {
				list.remove(p);
			}
		});
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + ":" + list.toString());

	}

	/**
	 * 增强版for循环删除
	 * 不能删除 报错 java.util.ConcurrentModificationException
	 */
	public static void deleteByEnhancedForLoop(List<String> list, Predicate<String> predicate) {
		for (String string : list) {
			if (predicate.test(string)) {
				list.remove(string);
			}
		}
	}


	/**
	 * 调用批量删除方法
	 */
	public static void deleteAll(List<String> list, Predicate<String> predicate) {
		List<String> removeList = new ArrayList<>();
		for (String string : list) {
			if (predicate.test(string)) {
				removeList.add(string);
			}
		}
		list.removeAll(removeList);
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + ":" + list.toString());

	}

}
