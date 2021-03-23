package com.code.zookeeper.test;

import com.code.zookeeper.ZookeeperApplicationTest;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.junit.Before;

/**
 * @author 愆凡
 * @date 2020/11/20 10:26 上午
 */
@Slf4j
public class LockTest extends ZookeeperApplicationTest {

	public static final String ZOOKEEPER_ADDERS = "linux.notuptoyou.site:12181";
	public static final String NAME_SPACE = "zkLockTest";
	public static final String NODE_PER_CODE = "/";
	public static CuratorFramework client = null;

	@Before
	public void init() {
		client = CuratorFrameworkFactory.builder()
				.connectString(ZOOKEEPER_ADDERS)
				.namespace(NAME_SPACE)
				.sessionTimeoutMs(5000)
				.connectionTimeoutMs(5000)
				.retryPolicy(new ExponentialBackoffRetry(1000, 3))
				.build();

		client.start();
	}

	public void tryLock() throws Exception {
		client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath("/test-4");
	}

}
