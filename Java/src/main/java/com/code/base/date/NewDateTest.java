package com.code.base.date;

import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * @author 愆凡
 * @date 2020/7/17 4:36 下午
 */
public class NewDateTest {

	@Test
	public void localDateTest() {
		// 获取今天的日期：yyyy-MM-dd
		LocalDate todayDate = LocalDate.now();
		System.err.println("-01--->  今天的日期 : " + todayDate);


		// 获取今天的 年、月、日
		int year = todayDate.getYear();
		int month = todayDate.getMonthValue();
		int day = todayDate.getDayOfMonth();
		System.err.printf("-02--->  年：%d ，月：%d ，日：%d \n", year, month, day);


		// 计算一周后的日期
		todayDate.plus(1, ChronoUnit.WEEKS);
		System.err.println("-03--->  今天一周后的日期 : " + todayDate);


		// 比较日期
		LocalDate test = LocalDate.of(2020, 11, 28);
		System.err.println("-04--->  " + todayDate.equals(test)); // 日期是否相同
		System.err.println("-04--->  " + todayDate.isAfter(test)); // today 是否晚于 2018-01-21
		System.err.println("-04--->  " + todayDate.isBefore(test)); // today 是否早于 2018-01-21


		// 获取日期间相隔的天数
		System.err.println("-05--->  相隔的天数：" + Math.abs(todayDate.toEpochDay() - test.toEpochDay()));
	}

	@Test
	public void localTimeTest() {
		LocalTime time = LocalTime.now();
		System.out.println("现在的时间 : " + time);


		LocalTime test = time.plusHours(2); // 增加两小时
		System.out.println("增加两小时 : " + test);
	}

	@Test
	public void lcoalDateTimeTest() {
		// 获取今天的日期时间：yyyy-MM-ddTHH:mm:ss.xxx
		LocalDateTime todayDateTime = LocalDateTime.now();
		System.err.println("-01--->  今天的日期时间 : " + todayDateTime);
		System.err.println("-01--->  今天的日期时间 : " + todayDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
	}

	@Test
	public void yearMonthTest() {
		YearMonth todayYearMonth = YearMonth.now();
		System.err.println("-01--->  今天的年月 : " + todayYearMonth);
	}

}
