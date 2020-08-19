package com.code.orm.jpa.repository;

import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.SecureUtil;
import com.code.orm.jpa.OrmJpaApplicationTest;
import com.code.orm.jpa.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigInteger;
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

	@Autowired
//	@PersistenceContext
	private EntityManager entityManager;

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

	/**
	 * 动态 SQL
	 */
	@Test
	@SuppressWarnings("all")
	public void sqlSelectTest() {
		// 参数
		String name = "test%";
		ArrayList<Integer> ages = new ArrayList<>();
		ages.add(5);

		// 创建查询SQL
		StringBuilder selectSql = new StringBuilder("SELECT * FROM user");
		StringBuilder countSql = new StringBuilder("SELECT COUNT(*) FROM user");
		StringBuilder whereSql = new StringBuilder(" WHERE 1 = 1");

		// 根据条件拼接 Where SQL
		if (StringUtils.isNotBlank(name)) {
			whereSql.append(" AND name LIKE '").append(name).append("'");
		}
		if (!ages.isEmpty()) {
			whereSql.append(" AND age IN :age");
		}

		// 拼接 Where
		selectSql = selectSql.append(whereSql);
		countSql = countSql.append(whereSql);

		//创建本地 sql 查询实例
		Query dataQuery = entityManager.createNativeQuery(selectSql.toString());
		Query countQuery = entityManager.createNativeQuery(countSql.toString());

		if (!ages.isEmpty()) {
			dataQuery.setParameter("age", ages);
			countQuery.setParameter("age", ages);
		}

		// 获取查询结果
		List resultList = dataQuery.getResultList();
		BigInteger count = (BigInteger) countQuery.getSingleResult();

		Assert.assertEquals(count.intValue(), 1);
		Assert.assertEquals(resultList.size(), 1);
	}

}
