package com.code.data.jpa.basic.repository;

import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.SecureUtil;
import com.code.data.jpa.basic.DataJpaBasicApplicationTest;
import com.code.data.jpa.basic.entity.User;
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
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author 愆凡
 * @date 2020/8/7 10:58 上午
 */
@Slf4j
public class UserRepositoryTest extends DataJpaBasicApplicationTest {

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
	 * 使用 别名 拼接参数
	 */
	@Test
	@SuppressWarnings("all")
	public void sqlSelectTest1() {
		// 参数
		String name = "test";
		Boolean isActive = true;
		ArrayList<Integer> ages = new ArrayList<>();
		ages.add(5);

		// 创建查询SQL
		StringBuilder selectSql = new StringBuilder("SELECT * FROM user");
		StringBuilder countSql = new StringBuilder("SELECT COUNT(*) FROM user");
		StringBuilder whereSql = new StringBuilder(" WHERE 1 = 1");

		// 根据条件拼接 Where SQL
		if (StringUtils.isNotBlank(name)) {
			whereSql.append(" AND name LIKE '").append(name).append("%'");
		}
		if (!ages.isEmpty()) {
			whereSql.append(" AND age IN :age");
		}
		if (isActive != null) {
			whereSql.append(" AND is_active = :isActive");
		}

		// 拼接 Where
		selectSql = selectSql.append(whereSql);
		countSql = countSql.append(whereSql);

		log.debug("Select Sql : " + selectSql.toString());
		log.debug("Count Sql : " + countSql.toString());

		//创建本地 sql 查询实例
		Query dataQuery = entityManager.createNativeQuery(selectSql.toString());
		Query countQuery = entityManager.createNativeQuery(countSql.toString());

		if (!ages.isEmpty()) {
			dataQuery.setParameter("age", ages);
			countQuery.setParameter("age", ages);
		}
		if (isActive != null) {
			dataQuery.setParameter("isActive", isActive);
			countQuery.setParameter("isActive", isActive);
		}

		// 获取查询结果
		List<Object[]> resultList = dataQuery.getResultList();
		BigInteger count = (BigInteger) countQuery.getSingleResult();

		log.debug(Arrays.toString(resultList.toArray()));

		Assert.assertEquals(count.intValue(), 1);
		Assert.assertEquals(resultList.size(), 1);
	}

	/**
	 * 动态 SQL
	 * 使用 位置编号 拼接参数
	 */
	@Test
	@SuppressWarnings("all")
	public void sqlSelectTest2() {
		// 参数
		String name = "test";
		Boolean isActive = true;
		ArrayList<Integer> ages = new ArrayList<>();
		ages.add(5);

		// 创建查询SQL
		StringBuilder selectSql = new StringBuilder("SELECT * FROM user");
		StringBuilder countSql = new StringBuilder("SELECT COUNT(*) FROM user");
		StringBuilder whereSql = new StringBuilder(" WHERE 1 = 1");

		// 根据条件拼接 Where SQL
		if (StringUtils.isNotBlank(name)) {
			whereSql.append(" AND name LIKE '").append(name).append("%'");
		}
		if (!ages.isEmpty()) {
			whereSql.append(" AND age IN ?1");
		}
		if (isActive != null) {
			whereSql.append(" AND is_active = ?2");
		}

		// 拼接 Where
		selectSql = selectSql.append(whereSql);
		countSql = countSql.append(whereSql);

		log.debug("Select Sql : " + selectSql.toString());
		log.debug("Count Sql : " + countSql.toString());

		//创建本地 sql 查询实例
		Query dataQuery = entityManager.createNativeQuery(selectSql.toString());
		Query countQuery = entityManager.createNativeQuery(countSql.toString());

		if (!ages.isEmpty()) {
			dataQuery.setParameter(1, ages);
			countQuery.setParameter(1, ages);
		}
		if (isActive != null) {
			dataQuery.setParameter(2, isActive);
			countQuery.setParameter(2, isActive);
		}

		// 获取查询结果
		List<Object[]> resultList = dataQuery.getResultList();
		BigInteger count = (BigInteger) countQuery.getSingleResult();

		log.debug(Arrays.toString(resultList.toArray()));

		Assert.assertEquals(count.intValue(), 1);
		Assert.assertEquals(resultList.size(), 1);
	}

}
