package com.code.zookeeper.client;

import com.code.zookeeper.ZookeeperApplicationTest;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author 愆凡
 * @date 2020/11/9 4:59 下午
 */
@Slf4j
public class ClientTest extends ZookeeperApplicationTest {

	public static final String ZOOKEEPER_ADDERS = "127.0.0.1:2181";
	public static CuratorFramework client = null;

	@Before
	public void init() {
		client = CuratorFrameworkFactory.builder()
				.connectString(ZOOKEEPER_ADDERS)
				.namespace("test")
				.sessionTimeoutMs(5000)
				.connectionTimeoutMs(5000)
				.retryPolicy(new ExponentialBackoffRetry(1000, 3))
				.build();

		client.start();
	}

	@After
	public void destroy() {
		client.close();
	}

	@Test
	public void initTest() {
		log.info("init success !");
	}

}
