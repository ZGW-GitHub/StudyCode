package com.code.zookeeper.client;

import com.code.zookeeper.ZookeeperApplicationTest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Scanner;

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
	public void destroy() throws Exception {
		Scanner scanner = new Scanner(System.in);
		System.out.println("是否删除所有节点?（直接回车为删除，输入任意字符则不删除。）");
		String scanstr = scanner.nextLine();
		
		if (StringUtils.isBlank(scanstr)) {
			client.delete().deletingChildrenIfNeeded().forPath("/");
		}
		
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
