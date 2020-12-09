package com.code.version.java8.stream;

import com.code.entity.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author 愆凡
 * @date 2020/7/15 4:05 下午
 */
public class DemoTest {

	private static final List<User> USER_LIST = new ArrayList<>();

	@Before
	public void initData() {
		USER_LIST.add(new User(1, "test"));
		USER_LIST.add(new User(2, "test"));
		USER_LIST.add(new User(3, "test"));
		USER_LIST.add(new User(3, "test"));
		USER_LIST.add(new User(2, "test"));
		USER_LIST.add(new User(1, "test"));
	}

	@After
	public void destroy() {
		System.out.println("\n\nTest Over !");
	}

	/**
	 * 测试排序、截取
	 */
	@Test
	public void sortTest() {
		List<User> collect = USER_LIST.stream()
				.sorted(Comparator.comparing(User::getAge))
				.collect(Collectors.toList());

		collect.forEach(user -> System.out.println(user.getAge()));

		System.out.println("---");

		collect = collect.subList(4, collect.size());
		collect.forEach(user -> System.out.println(user.getAge()));
		System.out.println(collect.size());
	}

	/**
	 * 测试分组
	 */
	@Test
	public void groupByTest() {
		Map<Integer, Map<Integer, String>> userMap = USER_LIST.stream()
				.collect(Collectors.groupingBy(User::getAge, Collectors.toMap(User::getAge, User::getName, (v1, v2) -> v2)));

		userMap.forEach((k, v) -> {
			System.out.println(k);

			v.forEach((k1, v1) -> {
				System.out.println("-- " + k1);
				System.out.println("---- " + v1);
			});
		});

		System.out.println("---------------------------");

		Map<Integer, List<String>> userMapList = USER_LIST.stream()
				.collect(Collectors.groupingBy(User::getAge, Collectors.mapping(User::getName, Collectors.toList())));

		userMapList.forEach((k, v) -> {
			System.out.println(k);
			System.out.println(Arrays.toString(v.toArray()));
		});

		System.out.println("---------------------------");

		Map<Integer, Set<String>> userMapSet = USER_LIST.stream()
				.collect(Collectors.groupingBy(User::getAge, Collectors.mapping(User::getName, Collectors.toSet())));

		userMapSet.forEach((k, v) -> {
			System.out.println(k);
			System.out.println(Arrays.toString(v.toArray()));
		});
	}

	@Test
	public void skipAndLimitTest() {
		USER_LIST.stream().skip(2).forEach(u -> System.out.println(u.getAge()));
		System.out.println("---");
		USER_LIST.stream().limit(2).forEach(u -> System.out.println(u.getAge()));
	}

}
