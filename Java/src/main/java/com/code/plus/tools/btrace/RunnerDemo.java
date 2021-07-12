package com.code.plus.tools.btrace;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author 愆凡
 * @date 2021/7/12 11:50
 */
@Slf4j
public class RunnerDemo {

	public static void main(String[] args) throws InterruptedException {
		RunnerDemo runnerDemo = new RunnerDemo();
		
		Random random = new Random();

		for (int i = 0; i < 300; i++) {
			runnerDemo.sleep(random.nextInt(5000));
		}
	}

	public long sleep(long time) throws InterruptedException {
		System.err.println("Time : " + time);
		TimeUnit.MILLISECONDS.sleep(time);
		return time;
	}
	
}
