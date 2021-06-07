package com.code.version.java8.stream;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author 愆凡
 * @date 2021/6/7 14:27
 */
@Slf4j
public class ParallelTest {

	@Test
	public void errorTest() {
		for (int j = 0; j < 10; j++) {
			List<Integer> integers = IntStream.rangeClosed(0, 1000).boxed().collect(Collectors.toList());

			List<String> strings = new ArrayList<>();
			integers.parallelStream().forEach(i -> strings.add(i.toString()));

			System.out.println(strings); // 打印的长度不一致或抛异常
		}
	}

	@Test
	public void rightTest() {
		for (int j = 0; j < 10; j++) {
			List<Integer> integers = IntStream.rangeClosed(0, 1000).boxed().collect(Collectors.toList());

			List<String> strings = integers.parallelStream().map(Object::toString).collect(Collectors.toList());

			System.out.println(strings);
		}
	}

}
