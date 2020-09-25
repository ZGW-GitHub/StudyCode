package com.code.version.java8.stream;

import com.code.entity.User;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 愆凡
 * @date 2020/7/15 4:05 下午
 */
public class DemoTest {

	/**
	 * 测试排序、截取
	 */
	@Test
	public void sortTest() {
		List<User> users = new ArrayList<>();
		users.add(new User(12, "test"));
		users.add(new User(13, "test"));
		users.add(new User(6, "test"));
		users.add(new User(2, "test"));
		users.add(new User(13, "test"));
		users.add(new User(16, "test"));

		List<User> collect = users.stream()
				.sorted(Comparator.comparing(user -> user.getAge() * (-1)))
				.collect(Collectors.toList());

		collect.forEach(user -> System.out.println(user.getAge()));

		System.out.println("---");

		collect = collect.subList(4, collect.size());
		collect.forEach(user -> System.out.println(user.getAge()));
		System.out.println(collect.size());
	}

}
