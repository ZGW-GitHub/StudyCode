package com.code.spring.aa.resource.spring;

import org.springframework.util.PathMatcher;

import java.util.Comparator;
import java.util.Map;

/**
 * 自定义路径匹配器：Java 路径匹配器
 *
 * @author 愆凡
 * @date 2021/3/25 14:33
 */
@SuppressWarnings("all")
public class JavaPathMatcher implements PathMatcher {

	@Override
	public boolean isPattern(String path) {
		return path.contains("*");
	}

	@Override
	public boolean match(String pattern, String path) {
		return path.endsWith("Test.java");
	}

	@Override
	public boolean matchStart(String pattern, String path) {
		return false;
	}

	@Override
	public String extractPathWithinPattern(String pattern, String path) {
		return null;
	}

	@Override
	public Map<String, String> extractUriTemplateVariables(String pattern, String path) {
		return null;
	}

	@Override
	public Comparator<String> getPatternComparator(String path) {
		return null;
	}

	@Override
	public String combine(String pattern1, String pattern2) {
		return null;
	}
}
