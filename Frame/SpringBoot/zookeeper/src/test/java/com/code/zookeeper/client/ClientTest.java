package com.code.zookeeper.client;

import com.code.zookeeper.ZookeeperApplicationTest;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
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
	public static final String NAME_SPACE = "test";
	public static final String NODE_PER_CODE = "/";
	public static CuratorFramework client = null;

	@Before
	public void init() throws Exception {
		client = CuratorFrameworkFactory.builder()
				.connectString(ZOOKEEPER_ADDERS)
				.namespace(NAME_SPACE)
				.sessionTimeoutMs(5000)
				.connectionTimeoutMs(5000)
				.retryPolicy(new ExponentialBackoffRetry(1000, 3))
				.build();

		client.start();

		client.getChildren().forPath("/").forEach(node -> {
			try {
				System.out.println(node);
				client.delete().deletingChildrenIfNeeded().forPath(NODE_PER_CODE + node);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	@After
	public void destroy() {
		client.close();
	}

	@Test
	public void initTest() {
		log.info("init success !");
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
		String nodeName1 = client.create().forPath("/test-1");
		log.info("test-1 : " + nodeName1);

		String nodeName2 = client.create().forPath("/test-2", "data".getBytes());
		log.info("test-2 : " + nodeName2);

		String nodeName3 = client.create().withMode(CreateMode.EPHEMERAL).forPath("/test-3");
		log.info("test-3 : " + nodeName3);

		String nodeName4 = client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath("/test-4");
		log.info("test-4 : " + nodeName4);
	}

}
