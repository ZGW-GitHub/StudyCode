package com.code.zookeeper.test;

import com.code.zookeeper.ZookeeperApplicationTest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.junit.Before;
import org.junit.Test;

/**
 * @author 愆凡
 * @date 2020/11/9 4:59 下午
 */
@Slf4j
public class ClientTest extends ZookeeperApplicationTest {

	public static final String ZOOKEEPER_ADDERS = "linux.notuptoyou.site:12181";
	public static final String NAME_SPACE = "zkCliTest";
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

	@Test
	public void destroy() throws Exception {
		client.delete().guaranteed().deletingChildrenIfNeeded().forPath(NODE_PER_CODE);

		client.close();
	}

	/**
	 * <h3>Node Type :</h3>
	 * <pre>
	 * PERSISTENT：持久化
	 * PERSISTENT_SEQUENTIAL：持久化并且带序列号
	 * EPHEMERAL：临时
	 * EPHEMERAL_SEQUENTIAL：临时并且带序列号
	 * </pre>
	 *
	 * @throws Exception 异常
	 */
	@Test
	public void createNodeTest() throws Exception {

		client.create().forPath("/test-1");

		client.create().forPath("/test-2", "initData".getBytes());

		client.create().withMode(CreateMode.EPHEMERAL).forPath("/test-3");

		client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath("/test-4");

	}

	@Test
	public void deleteNodeTest() throws Exception {

		client.delete().forPath("/test-1");

		client.delete().guaranteed().forPath("/test-2");

		client.delete().withVersion(10086).forPath("/test-3");

		client.delete().deletingChildrenIfNeeded().forPath("/test-4");

		client.delete().guaranteed().deletingChildrenIfNeeded().withVersion(10086).forPath("/test-5");

	}

	@Test
	public void getNodeDataTest() throws Exception {
		client.create().forPath("/test", "initData".getBytes());

		byte[] datas1 = client.getData().forPath("/test");

		log.info("data : " + new String(datas1));


		Stat stat = new Stat();
		byte[] datas2 = client.getData().storingStatIn(stat).forPath("/test");

		log.info("data : " + new String(datas2));
		log.info("stat : " + ToStringBuilder.reflectionToString(stat));
	}

	@Test
	public void updateNodeDataTest() throws Exception {
		client.create().forPath("/test", "initData".getBytes());

		log.info("data : " + new String(client.getData().forPath("/test")));


		Stat stat1 = client.setData().forPath("/test", "updateData".getBytes());

		log.info("data : " + new String(client.getData().forPath("/test")));
		log.info("stat : " + ToStringBuilder.reflectionToString(stat1));


		Stat stat2 = client.setData().withVersion(1).forPath("/test", "updateData".getBytes());

		log.info("data : " + new String(client.getData().forPath("/test")));
		log.info("stat : " + ToStringBuilder.reflectionToString(stat2));
	}

	@Test
	public void checkNodeTest() throws Exception {
		Stat stat1 = client.checkExists().forPath("/test");

		log.info("stat1 : " + (stat1 == null ? "" : ToStringBuilder.reflectionToString(stat1)));

		client.create().forPath("/test", "initData".getBytes());

		Stat stat2 = client.checkExists().forPath("/test");

		log.info("stat2 : " + ToStringBuilder.reflectionToString(stat2));
	}

	@Test
	public void chiledNodeTest() throws Exception {
		client.getChildren().forPath("/").forEach(path -> log.info("path : " + path));

		client.create().forPath("/test");

		client.getChildren().forPath("/").forEach(path -> log.info("path : " + path));
	}

	@Test
	public void transactionTest() {

	}

	@Test
	public void asyncTest() throws Exception {
		client.create().inBackground((cli, event) -> log.info("eventType : " + event.getType() + " , resultCode : " + event.getResultCode())).forPath("/test");

		Thread.currentThread().join();
	}

}
