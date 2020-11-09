package com.code.data.mybatis.basic.mapper;

import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.SecureUtil;
import com.code.data.mybatis.basic.DataMybatisBasicApplicationTest;
import com.code.data.mybatis.basic.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * @author 愆凡
 * @date 2020/8/14 2:42 下午
 */
@Slf4j
public class UserMapperTest extends DataMybatisBasicApplicationTest {

	@Autowired
	private UserMapper userMapper;

	@Before
	public void init() {
		String salt = IdUtil.fastSimpleUUID();

		User user = User.builder().name("test").age(10).isActive(true).sex(User.SEX_MAN)
				.phone(SecureUtil.md5(System.currentTimeMillis() + salt)).salt(salt)
				.createTime(new Date()).lastUpdateTime(new Date()).build();

		userMapper.saveUser(user);
	}

	@After
	public void destroy() {
		userMapper.deleteAll();
	}

	@Test
	public void initTest() {
		System.out.println("init success !");
	}

	@Test
	public void listUserTest() {
		List<User> users = userMapper.listUser();

		Assert.assertEquals(users.size(), 1);
	}

}
