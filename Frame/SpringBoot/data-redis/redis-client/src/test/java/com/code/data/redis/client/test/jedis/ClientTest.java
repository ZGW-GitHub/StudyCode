package com.code.data.redis.client.test.jedis;

import com.code.data.redis.client.RedisClientApplicationTest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPubSub;

import java.util.stream.IntStream;

/**
 * @author 愆凡
 * @date 2021/3/11 16:06
 */
public class ClientTest extends RedisClientApplicationTest {

	@Autowired
	private JedisPool jedisPool;

	private Jedis jedis = null;

	@Before
	public void before() {
		jedis = jedisPool.getResource();
	}

	/**
	 * Hash 表操作
	 */
	@Test
	public void hashTest() {
		Boolean hexists = jedis.hexists("key", "field");

		// 递增1
		Long hincr = jedis.hincrBy("key", "field", 1);
		System.err.println(hincr);

		// 设置值
		Long hset = jedis.hset("key", "field", "test2");
		System.err.println(hset);
	}

	/**
	 * HyperLogLog 操作
	 */
	@Test
	public void HyperLogLogTest() {
		IntStream.range(0, 10).forEach(i -> jedis.pfadd("test", i + ""));

		System.err.println(jedis.pfcount("test"));
	}

	/**
	 * 订阅主题
	 */
	@Test
	public void subscribeTest() throws InterruptedException {
		jedis.subscribe(new JedisPubSub() {
			// 取得订阅的消息后的处理
			@Override
			public void onMessage(String channel, String message) {
				System.out.println(channel + "=" + message);
			}

			// 初始化订阅时候的处理
			@Override
			public void onSubscribe(String channel, int subscribedChannels) {
				// System.out.println(channel + "=" + subscribedChannels);
			}

			// 取消订阅时候的处理
			@Override
			public void onUnsubscribe(String channel, int subscribedChannels) {
				// System.out.println(channel + "=" + subscribedChannels);
			}

			// 初始化按表达式的方式订阅时候的处理
			@Override
			public void onPSubscribe(String pattern, int subscribedChannels) {
				// System.out.println(pattern + "=" + subscribedChannels);
			}

			// 取消按表达式的方式订阅时候的处理
			@Override
			public void onPUnsubscribe(String pattern, int subscribedChannels) {
				// System.out.println(pattern + "=" + subscribedChannels);
			}

			// 取得按表达式的方式订阅的消息后的处理
			@Override
			public void onPMessage(String pattern, String channel, String message) {
				System.out.println(pattern + "=" + channel + "=" + message);
			}
		}, "test");

		Thread.currentThread().join();
	}

	/**
	 * 向主题发布消息
	 */
	@Test
	public void publishTest() {
		jedis.publish("test", "testMsg");
	}

}
