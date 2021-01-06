package com.code.data.jpa.basic.repository;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.code.data.jpa.basic.DataJpaBasicApplicationTest;
import com.code.data.jpa.basic.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

	@Before
	public void init() {
		List<User> users = IntStream.rangeClosed(1, 10).boxed().map(i -> {
			String salt = IdUtil.fastSimpleUUID();
			return User.builder().name("test" + i).age(i).isActive(true).sex(User.SEX_MAN)
					.phone(SecureUtil.md5(System.currentTimeMillis() + salt)).salt(salt)
					.createTime(new Date()).lastUpdateTime(new Date()).build();
		}).collect(Collectors.toList());

		userRepository.saveAll(users);
	}

	@After
	public void destroy() {
		userRepository.deleteAll();
	}

	@Test
	public void initTest() {
		log.info("init success !");
	}
	
	@Test
	@Transactional
	@Rollback(value = false)
	public void demoTest() {
		Integer sex = userRepository.findSexByName("test1");

		System.err.println("sex : " + sex);
	}

	@Test
	public void selectTest() {
		List<User> users = userRepository.findAll();

		Assert.assertEquals(users.size(), 10);
	}

	/**
	 * 动态 SQL
	 * <p></p>
	 * 使用 别名 拼接参数
	 */
	@Test
	@SuppressWarnings("all")
	public void sqlSelectTest1() {
		// 参数
		String name = "test";
		Boolean isActive = true;
		List<Integer> ages = IntStream.range(3, 6).boxed().collect(Collectors.toList());

		// 创建查询SQL
		StringBuilder selectSql = new StringBuilder(" SELECT * FROM user ");
		StringBuilder countSql = new StringBuilder(" SELECT COUNT(*) FROM user ");
		StringBuilder whereSql = new StringBuilder(" WHERE 1 = 1 ");

		// 根据条件拼接 Where SQL
		if (StrUtil.isNotBlank(name)) {
			whereSql.append(" AND name LIKE '").append(name).append("%'");
		}
		if (!ages.isEmpty()) {
			whereSql.append(" AND age IN :age ");
		}
		if (isActive != null) {
			whereSql.append(" AND is_active = :isActive ");
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

		log.error(Arrays.toString(resultList.toArray()));

		Assert.assertEquals(count.intValue(), 3);
		Assert.assertEquals(resultList.size(), 3);
	}

	/**
	 * 动态 SQL
	 * <p></p>
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
		if (StrUtil.isNotBlank(name)) {
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
