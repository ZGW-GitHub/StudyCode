package com.code.demo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author 愆凡
 * @date 2020/7/15 5:08 下午
 */
@SuppressWarnings("all")
public class Demo {
	public static void main(String[] args) {

		List<User> users = new ArrayList<>();
		users.add(new User(12, "test"));
		users.add(new User(13, "test"));
		users.add(new User(6, "test"));
		users.add(new User(2, "test"));
		users.add(new User(13, "test"));
		users.add(new User(16, "test"));

		Map<String, List<User>> collect1 = users.stream().collect(Collectors.groupingBy(u -> u.getName() + u.getAge()));

		List<User> collect = users.stream().sorted(Comparator.comparing(user -> user.getAge() * (-1))).collect(Collectors.toList());
		collect.forEach(user -> System.out.println(user.getAge()));

		System.out.println("---");

		collect = collect.subList(4, collect.size());
		collect.forEach(user -> System.out.println(user.getAge()));
		System.out.println(collect.size());

		System.out.println("----");

		String format = String.format("%.3f", 20 * 1.0 / 3);
		System.out.println(format);

		System.out.println("-----");

		String sub = "测试截取1";
		String subed = sub.length() > 4 ? sub.substring(0, 4) : sub;
		System.out.println(subed);

	}
}
