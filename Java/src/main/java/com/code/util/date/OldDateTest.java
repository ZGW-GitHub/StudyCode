package com.code.util.date;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author 愆凡
 * @date 2020/12/29 16:03
 */
public class OldDateTest {

	@Test
	public void test() {
		Calendar calendar = Calendar.getInstance(); // 获取当前日历


		// 获取今天的日期时间
		Date todayDateTime = calendar.getTime();
		System.err.println("-01--->  今天的日期 : " + todayDateTime);


		// 获取今天的 年、月、日
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		System.err.printf("-02--->  年：%d ，月：%d ，日：%d \n", year, month, day);


		// 格式化
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.err.println("-03--->  今天的时间(格式化后) : " + format.format(calendar.getTime()));


		// 计算日期时间
		calendar.add(Calendar.MONTH, -8); // 获取 N 月后的日期时间
		System.err.println("-04--->  计算后的时间 : " + format.format(calendar.getTime()));
		calendar.add(Calendar.DAY_OF_YEAR, -8); // 获取 N 天后的日期时间
		System.err.println("-04--->  计算后的时间 : " + format.format(calendar.getTime()));
		calendar.add(Calendar.HOUR, -8); // 获取 N 小时后的日期时间
		System.err.println("-04--->  计算后的时间 : " + format.format(calendar.getTime()));
	}

}
