package com.code.collection.basic.unmodifiable;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

/**
 * @author 愆凡
 * @date 2021/6/7 11:43
 */
@Slf4j
public class UnmodifiableTest {

	@Test
	@SuppressWarnings("all")
	public void test() {
		List<String> stringList = Collections.unmodifiableList(List.of("1", "2"));

		stringList.forEach(System.err::println);

//		stringList.add("3");
	}

}
