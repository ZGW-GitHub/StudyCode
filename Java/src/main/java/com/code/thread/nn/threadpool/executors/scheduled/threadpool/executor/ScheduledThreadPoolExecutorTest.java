package com.code.thread.nn.threadpool.executors.scheduled.threadpool.executor;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * @author 愆凡
 * @date 2020/5/11 9:14 上午
 */
public class ScheduledThreadPoolExecutorTest {

	private final ScheduledExecutorService delegatedScheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

	private final ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(3);

}
