package com.code.data.tool.sharding.jdbc.repository;

import com.code.data.tool.sharding.jdbc.DataToolShardingJdbcApplicationTest;
import com.code.data.tool.sharding.jdbc.entity.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 分库分表测试
 * 
 * @author 愆凡
 * @date 2021/3/9 16:33
 */
public class ShardingTest extends DataToolShardingJdbcApplicationTest {
	
	@Autowired
	private UserRepository userRepository;
	
	@Test
	public void test() {
		List<User> userList = userRepository.findAll();

		System.err.println(userList.size());
	}
	
}
