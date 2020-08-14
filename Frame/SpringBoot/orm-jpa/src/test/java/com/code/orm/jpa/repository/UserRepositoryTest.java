package com.code.orm.jpa.repository;

import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.SecureUtil;
import com.code.orm.jpa.OrmJpaApplicationTest;
import com.code.orm.jpa.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author 愆凡
 * @date 2020/8/7 10:58 上午
 */
@Slf4j
public class UserRepositoryTest extends OrmJpaApplicationTest {

	@Autowired
	private UserRepository userRepository;

	@Test
	public void saveAllTest() {
		ArrayList<User> users = Lists.newArrayList();

		for (int i = 1; i < 10; i++) {
			String salt = IdUtil.fastSimpleUUID();
			User user = User.builder().name("test" + i).age(i).isActive(true).sex(User.SEX_MAN)
					.phone(SecureUtil.md5(System.currentTimeMillis() + salt)).salt(salt)
					.createTime(new Date()).lastUpdateTime(new Date()).build();
			users.add(user);
		}

		userRepository.saveAll(users);
	}

	@Test
	public void selectTest() {
		List<User> users = userRepository.findAll();

		Assert.assertNotEquals(users.size(), 0);
	}

}
