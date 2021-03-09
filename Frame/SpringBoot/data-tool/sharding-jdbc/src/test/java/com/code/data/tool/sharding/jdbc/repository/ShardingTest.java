package com.code.data.tool.sharding.jdbc.repository;

import com.code.data.tool.sharding.jdbc.DataToolShardingJdbcApplicationTest;
import com.code.data.tool.sharding.jdbc.entity.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

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
	public void insertTest() {
		List<User> userList = new ArrayList<>();
		
		IntStream.range(6, 16).forEach(i -> userList.add(User.builder().name("test").age(i).build()));
		
		userRepository.saveAll(userList);
	}
	
	@Test
	public void selectTest() {
		List<User> userList = userRepository.findAll();

		System.err.println(userList.size());
	}
	
}
