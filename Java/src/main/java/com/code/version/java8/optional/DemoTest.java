package com.code.version.java8.optional;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Optional;

/**
 * @author 愆凡
 * @date 2020/11/23 4:17 下午
 */
@Slf4j
@SuppressWarnings("all")
public class DemoTest {

	@Test
	public void createTest() {
		// 返回一个：包含 test 字符串的 Optional
		Optional<String> o1 = Optional.of("test");
		// 抛异常
		Optional<Object> o2 = Optional.of(null);
		

		System.out.println("------------------");

		
		// 返回一个：包含 test 字符串的 Optional
		Optional<String> o3 = Optional.ofNullable("test");
		// 返回一个：空 Optional
		Optional<Object> o4 = Optional.ofNullable(null);
	}

	@Test
	public void checkTest() {
		// Optional 不为空返回 true
		boolean boo = Optional.of("test").isPresent();
	}

	@Test
	public void getTest() {
		// Optional 不为空则返回，为空则抛异常
		String str1 = Optional.of("test").get();


		System.out.println("------------------");


		// Optional 不为空则返回test，为空则返回test2
		String str2 = Optional.of("test").orElse("test2");


		System.out.println("------------------");


		// Optional 不为空则返回test，为空则返回Supplier函数返回的值
		String str3 = Optional.of("test").orElseGet(() -> "test3");


		System.out.println("------------------");


		// Optional 不为空则返回test，为空则抛出Supplier函数返回的异常
		String str4 = Optional.of("test").orElseThrow(() -> new RuntimeException());
	}
	
	@Test
	public void consumerTest() {
		// Optional 不为空则执行consumer函数，为空则不做处理
		Optional.of("test").ifPresent(str -> System.out.println(str));


		System.out.println("------------------");


		// Optional 不为空则执行 mapping 函数，返回的 Optional 是否包含值取决于 mapping 函数的返回值是否为 null
		Optional<String> test1 = Optional.of("test").map(str -> str.toUpperCase());


		System.out.println("------------------");


		// 与 map 的区别在于：
		// map 会将 mapping 函数的返回值包装为 Optional ，所以我们在编写 map 的 mapping 函数时返回值可以是任意类型
		// flatMap 不会这样，所以我们在编写 flatMap 的 mapping 函数时必须手动将返回值包装为 Optional 类型
		Optional<String> test2 = Optional.of("test").flatMap(str -> Optional.of(str.toUpperCase()));
	}
	
	@Test
	public void filteTest() {
		// 如果 Optional 有值并且满足条件则返回包含该值的 Optional ，否则返回空 Optional
		Optional<String> test = Optional.of("test").filter(str -> str.length() > 2);
	}

}
