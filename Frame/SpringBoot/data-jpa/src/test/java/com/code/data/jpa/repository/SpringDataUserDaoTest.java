package com.code.data.jpa.repository;

import cn.hutool.core.util.IdUtil;
import com.code.data.jpa.SpringBootDataJpaApplicationTest;
import com.code.data.jpa.entity.SpringDataUser;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author 愆凡
 * @date 2020/8/7 10:58 上午
 */
@Slf4j
public class SpringDataUserDaoTest extends SpringBootDataJpaApplicationTest {

	@Autowired
	private SpringDataUserDao springDataUserDao;

	public void initData() {
		ArrayList<SpringDataUser> users = Lists.newArrayList();

		for (int i = 1; i < 10; i++) {
			SpringDataUser user = SpringDataUser.builder().name("test" + i).age(i).isActive(true)
					.sex(SpringDataUser.SEX_MAN).phone("1310000000" + i).createTime(new Date()).build();
			users.add(user);
		}

		springDataUserDao.saveAll(users);
	}

	@Test
	public void selectTest() {
		List<SpringDataUser> users = springDataUserDao.findAll();

		Assert.assertNotNull(users);

		users.forEach(user -> System.out.println(user.toString()));
	}

}
