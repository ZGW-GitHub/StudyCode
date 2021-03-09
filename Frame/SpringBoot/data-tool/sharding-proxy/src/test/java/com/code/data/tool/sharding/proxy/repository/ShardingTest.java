package com.code.data.tool.sharding.proxy.repository;

import com.code.data.tool.sharding.proxy.DataToolShardingProxyApplicationTest;
import com.code.data.tool.sharding.proxy.entity.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 分库分表测试
 *
 * @author 愆凡
 * @date 2021/3/9 16:33
 */
public class ShardingTest extends DataToolShardingProxyApplicationTest {

	@Autowired
	private UserRepository userRepository;
	
	@Test
	public void insertTest() {

	}

	@Test
	public void selectTest() {
		List<User> userList = userRepository.findAll();

		userList.forEach(System.err::println);
	}

}
