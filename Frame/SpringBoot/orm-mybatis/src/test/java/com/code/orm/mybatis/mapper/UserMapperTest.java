package com.code.orm.mybatis.mapper;

import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.SecureUtil;
import com.code.orm.mybatis.OrmMybatisApplicationTest;
import com.code.orm.mybatis.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * @author 愆凡
 * @date 2020/8/14 2:42 下午
 */
@Slf4j
public class UserMapperTest extends OrmMybatisApplicationTest {

	@Autowired
	private UserMapper userMapper;

	@Test
	public void saveUserTest() {
		String salt = IdUtil.fastSimpleUUID();

		User user = User.builder().name("test").age(10).isActive(true).sex(User.SEX_MAN)
				.phone(SecureUtil.md5(System.currentTimeMillis() + salt)).salt(salt)
				.createTime(new Date()).lastUpdateTime(new Date()).build();

		Assert.assertEquals(userMapper.saveUser(user), 1);
	}

	@Test
	public void listUserTest() {
		List<User> users = userMapper.listUser();

		Assert.assertNotEquals(users.size(), 0);
	}

}
