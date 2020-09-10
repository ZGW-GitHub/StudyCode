package com.code.data.redis.cache.service;

import com.code.data.redis.cache.DataRedisCacheApplicationTest;
import com.code.data.redis.cache.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Redis - 缓存测试
 *
 * @author 愆凡
 * @date 2020/9/1 11:54 上午
 */
@Slf4j
public class UserServiceTestCache extends DataRedisCacheApplicationTest {

	@Autowired
	private UserService userService;

	/**
	 * 获取两次，查看日志验证缓存
	 */
	@Test
	public void getTwice() {
		// 模拟查询id为1的用户
		User user1 = userService.get(1L);
		log.debug("【user1】= {}", user1);

		// 再次查询
		User user2 = userService.get(1L);
		log.debug("【user2】= {}", user2);

		// 查看日志，只打印一次日志，证明缓存生效
	}

	/**
	 * 先存，再查询，查看日志验证缓存
	 */
	@Test
	public void getAfterSave() {
		userService.saveOrUpdate(User.builder().id(4L).name("user").build());

		User user = userService.get(4L);
		log.debug("【user】= {}", user);

		// 查看日志，只打印保存用户的日志，查询是未触发查询日志，因此缓存生效
	}

	/**
	 * 测试删除，查看 redis 是否存在缓存数据
	 */
	@Test
	public void deleteUser() {
		// 查询一次，使 redis 中存在缓存数据
		userService.get(1L);

		// 删除，查看 redis 是否存在缓存数据
		userService.delete(1L);
	}

}
