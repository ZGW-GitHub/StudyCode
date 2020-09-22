package com.code.thread.nn.threadpool.executor.threadpoll.executor;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class H_Executor_getQueue_Add {

	private static final ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newFixedThreadPool(1);


	public static void main(String[] args) {

//        testGetQueueAndAdd();

		testGetQueueAndAddAfterHaveActiveThread();

	}

	private static void testGetQueueAndAdd() {

		executorService.getQueue().add(() -> System.out.println("在没有活动线程的情况下直接向线程池内添加任务并不会执行该任务，因为该操作没有触发线程池创建活动线程！！！"));

	}

	private static void testGetQueueAndAddAfterHaveActiveThread() {

		executorService.execute(() -> System.out.println("提交任务，并触发线程池创建活动线程，接着执行该任务！！！"));

		executorService.getQueue().add(() -> System.out.println("因为存在活动线程，所以此任务会被执行！！！"));

	}

}
